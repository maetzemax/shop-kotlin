package com.maetzedev.shop_kotlin.repositories

import android.content.ContentValues
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maetzedev.shop_kotlin.models.status.Resource
import com.maetzedev.shop_kotlin.models.user.User
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class UserRepo {
    private val db = Firebase.firestore

    init {
        FirebaseApp.initializeApp(FirebaseApp.getInstance().applicationContext)
    }

    fun fetchUserData() = callbackFlow {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: throw Error()
        val collection = db.collection("users").document(uid)

        val snapshotListener = collection.addSnapshotListener { value, error ->

            val response = if (error == null && value != null) {
                val data = User.toObject(value)
                Resource.success(data)
            } else {
                Log.e(ContentValues.TAG, "Error fetching products", error)
                Resource.error(error.toString(), null)
            }

            this.trySend(response).isSuccess
        }

        awaitClose() {
            snapshotListener.remove()
        }
    }
}