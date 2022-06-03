package com.maetzedev.shop_kotlin.models.product

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject
import com.google.gson.Gson
import com.ramcosta.composedestinations.navargs.DestinationsNavTypeSerializer
import com.ramcosta.composedestinations.navargs.NavTypeSerializer

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
    var seller: String,
    var isLiked: Boolean? = false,
    ) {
    constructor() : this(docId = "0", id = 0, price = 0.00, description = "", name = "", seller = "", created = Timestamp.now(), isLiked = false) {}

    companion object {
        fun toObject(doc: DocumentSnapshot): Product? {
            val item = doc.toObject<Product>()
            item?.docId = doc.id
            return item
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