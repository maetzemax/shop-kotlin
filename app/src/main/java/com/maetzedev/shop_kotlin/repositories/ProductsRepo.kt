package com.maetzedev.shop_kotlin.repositories

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maetzedev.shop_kotlin.models.product.Product
import com.maetzedev.shop_kotlin.models.status.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow


class ProductsRepo {

    private val db = Firebase.firestore

    init {
        FirebaseApp.initializeApp(FirebaseApp.getInstance().applicationContext)
    }

    fun fetchProducts() = callbackFlow {
        val collection = db.collection("products")

        val snapshotListener = collection.orderBy("created", Query.Direction.DESCENDING).addSnapshotListener { value, error ->

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
