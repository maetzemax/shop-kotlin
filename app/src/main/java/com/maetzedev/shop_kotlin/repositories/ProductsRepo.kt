package com.maetzedev.shop_kotlin.repositories

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maetzedev.shop_kotlin.models.product.Product
import com.maetzedev.shop_kotlin.models.status.Resource
import com.maetzedev.shop_kotlin.models.user.User
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow


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
}
