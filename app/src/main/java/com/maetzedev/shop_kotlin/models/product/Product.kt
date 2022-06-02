package com.maetzedev.shop_kotlin.models.product

import android.util.Log
import androidx.compose.runtime.collectAsState
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.maetzedev.shop_kotlin.models.user.User
import com.maetzedev.shop_kotlin.repositories.UserRepo
import com.ramcosta.composedestinations.navargs.DestinationsNavTypeSerializer
import com.ramcosta.composedestinations.navargs.NavTypeSerializer
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect

/**
    Creates a product based on id, name, description, price and seller. (/D010/)
*/
data class Product(
    var docId: String,
    var id: Int,
    var created: Timestamp? = null,
    var name: String,
    var description: String,
    var price: Double,
    var seller: String
    ) {
    constructor() : this(docId = "0", id = 0, price = 0.00, description = "", name = "", seller = "", created = Timestamp.now()) {}

    companion object {
        fun toObject(doc: DocumentSnapshot): Product? {
            val item = doc.toObject<Product>()
            item?.docId = doc.id
            return item
        }
    }

    fun addToLikedProducts(likedProduct: Int) {

        Log.e("REACHED", "we are here party")
        val db = Firebase.firestore
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: throw Error()

        db.collection("users").document(uid).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.e("Successful", "Lets go")

                val likedProducts: List<Int> = task.result.data?.get("likedProducts") as List<Int>
                var mutableLikedProducts = likedProducts.toMutableList()
                var isInside = false

                likedProducts.forEach {
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
}


/*Provide the navigation argument serialization for the Product type.*/
@NavTypeSerializer
class ProductTypeSerializer: DestinationsNavTypeSerializer<Product> {

    private val gson = Gson()

    override fun toRouteString(value: Product): String =
        gson.toJson(value)

    override fun fromRouteString(routeStr: String): Product =
        gson.fromJson(routeStr, Product::class.java)

}