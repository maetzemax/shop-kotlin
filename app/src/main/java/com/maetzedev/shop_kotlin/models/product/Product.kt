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
    var price: Float,
    var seller: String
    ) {
    constructor() : this(docId = "0", id = 0, price = 0.00f, description = "", name = "", seller = "", created = Timestamp.now()) {}

    companion object {
        fun toObject(doc: DocumentSnapshot): Product? {
            val item = doc.toObject<Product>()
            item?.docId = doc.id
            return item
        }
    }
}



@NavTypeSerializer
class ProductTypeSerializer: DestinationsNavTypeSerializer<Product> {

    private val gson = Gson()

    override fun toRouteString(value: Product): String =
        gson.toJson(value)

    override fun fromRouteString(routeStr: String): Product =
        gson.fromJson(routeStr, Product::class.java)

}