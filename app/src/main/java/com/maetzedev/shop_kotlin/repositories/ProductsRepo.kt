package com.maetzedev.shop_kotlin.repositories

import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.maetzedev.shop_kotlin.models.product.Product
import com.maetzedev.shop_kotlin.models.status.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.util.*


class ProductsRepo {

    private val db = Firebase.firestore

    init {
        FirebaseApp.initializeApp(FirebaseApp.getInstance().applicationContext)
    }

    /**
     * This function fetch all the data from firestore based on the path "products".
     * Results and state of progress are stores in Resource.
     * */
    fun fetchProducts() = callbackFlow {
        val collection = db.collection("products")

        // Sorter which show the newest products first
        // TODO: Allow different sort algorithms (newest, lowest price...)
        val snapshotListener = collection.orderBy("created", Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->

                val response = if (error == null && value != null) {
                    val data = value.documents.map { doc ->
                        Product.toObject(doc)
                    }
                    Log.e("PRODUKT", "${data}")
                    Resource.success(data)
                } else {
                    Log.e(TAG, "Error fetching products", error)
                    Resource.error(error.toString(), null)
                }

                this.trySend(response).isSuccess
            }

        awaitClose() {
            snapshotListener.remove()
        }
    }

    fun addNewProduct(product: Product, imageByteArray: ByteArray) {
        val db = Firebase.firestore
        val username = FirebaseAuth.getInstance().currentUser?.displayName ?: throw Error("No UID")

        db.collection("products")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val productMapped = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        hashMapOf(
                            "created" to Timestamp.now(),
                            "name" to product.name,
                            "id" to UUID.randomUUID().variant(),
                            "description" to product.description,
                            "price" to product.price,
                            "seller" to username,
                            "docId" to product.docId,
                            "category" to product.category
                        )
                    } else {
                        TODO("VERSION.SDK_INT < O")
                    }

                    db
                        .collection("products")
                        .document("${product.docId}")
                        .set(productMapped)
                        .addOnSuccessListener {
                            Log.i("UPLOAD", "Erfolgreich hochgeladen")
                            saveImage(byteArray = imageByteArray, docId = product.docId)
                        }
                        .addOnCanceledListener {
                            Log.e("UPLOAD", "Upload konnte nicht erfolgreichdurchgeführt werden")
                        }

                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }
    }

    fun addToLikedProducts(likedProduct: Int) {
        val db = Firebase.firestore
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: throw Error("No UID")

        db.collection("users").document(uid).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {

                val likedProducts: List<Int> = task.result.data?.get("likedProducts") as List<Int>
                var mutableLikedProducts = likedProducts.toMutableList()
                var isInside = false

                // very weird way of checking but .contains does not work ¯\_(ツ)_/¯
                likedProducts.forEach { it ->
                    if (it - likedProduct == 0)  {
                        isInside = true
                        mutableLikedProducts = mutableLikedProducts.filter { it -> it != likedProduct }.toMutableList()
                    }
                }

                if (!isInside) {
                    mutableLikedProducts.add(likedProduct)
                }


                Log.e("updatedLikedProducts", mutableLikedProducts.toString())
                val collection = db.collection("users").document(uid).update("likedProducts", mutableLikedProducts)
            }
        }
    }

    fun fetchProductsById(Ids: List<Int>?) = callbackFlow {
        val collection = db.collection("products")

        // TODO: fix where in
        val snapshotListener = collection.whereIn("id", Ids!!).orderBy("created", Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->

                Log.e("VALUE", value.toString())

                val response = if (error == null && value != null) {
                    val data = value.documents.map { doc ->
                        Product.toObject(doc)
                    }
                    Log.e("PRODUKT", "${data}")
                    Resource.success(data)
                } else {
                    Log.e(TAG, "Error fetching products", error)
                    Resource.error(error.toString(), null)
                }

                this.trySend(response).isSuccess
            }

        awaitClose() {
            snapshotListener.remove()
        }
    }

    fun updateProductsCart(productId: Int) {
        val db = Firebase.firestore
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: throw Error("No UID")

        db.collection("users").document(uid).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val cartProducts: List<Int> = task.result.data?.get("cartProducts") as List<Int>
                var mutableCartProducts = cartProducts.toMutableList()
                var isInside = false

                // very weird way of checking but .contains does not work ¯\_(ツ)_/¯
                mutableCartProducts.forEach { product ->
                    if (product - productId == 0) {
                        isInside = true
                        mutableCartProducts = mutableCartProducts.filter {it -> it != productId }.toMutableList()
                    }
                }

                if (!isInside) {
                    mutableCartProducts.add(productId)
                }

                Log.e("updatedCart", mutableCartProducts.toString())
                val collection = db.collection("users").document(uid).update("cartProducts", mutableCartProducts)
            }
        }
    }

    fun saveImage(byteArray: ByteArray, docId: String) {
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.getReferenceFromUrl("gs://shop-2a2dd.appspot.com")
        val productsRef = storageRef.child("$docId.jpg")
        val productsSpaceRef = storageRef.child("productImages/$docId.jpg")

        productsRef.name == productsSpaceRef.name
        productsRef.path == productsSpaceRef.path

        productsRef
            .putBytes(byteArray)
            .addOnFailureListener { exp ->
                Log.e("Speichern", exp.toString())
            }
            .addOnSuccessListener { task ->
                Log.i("Speicher", "Erfolgreich")
                productImageURL(docId)
            }
    }

    fun productImageURL(productDocId: String) {
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val pathReference = storageRef.child("$productDocId.jpg")

        pathReference.downloadUrl.addOnSuccessListener {
            setImageUrl(it.toString(), productDocId)
        }
    }

    fun setImageUrl(downloadUrl: String, docId: String) {

        val downloadUrlMap = hashMapOf(
            "imageUrl" to downloadUrl
        )

        db.collection("products")
            .get()
            .addOnCompleteListener { task ->
                    db
                        .collection("products")
                        .document("${docId}")
                        .set(downloadUrlMap, SetOptions.merge())
                        .addOnSuccessListener {
                            Log.i("UPLOAD", "Erfolgreich hochgeladen")
                        }
                        .addOnCanceledListener {
                            Log.e("UPLOAD", "Upload konnte nicht erfolgreichdurchgeführt werden")
                        }

                }
            }
}
