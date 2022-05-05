package com.maetzedev.shop_kotlin.models.product

import androidx.compose.runtime.compositionLocalOf
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject
import java.util.*

/**
    Creates a product based on id, name, description, price and seller. (/D010/)
*/
data class Product(
    var id: String,
    var created: Timestamp? = null,
    var name: String,
    var description: String,
    var price: String,
    var seller: String
    ) {
    constructor() : this(id = "1", price = "100.99", description = "testbeschreibung", name = "testname", seller = "Maetzi", created = Timestamp.now()) {}

    companion object {
        fun toObject(doc: DocumentSnapshot): Product? {
            val item = doc.toObject<Product>()
            item?.id = doc.id
            return item
        }
    }
}