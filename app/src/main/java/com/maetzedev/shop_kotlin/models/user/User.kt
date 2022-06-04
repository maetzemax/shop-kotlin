package com.maetzedev.shop_kotlin.models.user

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject

data class User(
    var docId: String,
    var likedProducts: List<Int>,
    var cartProducts: List<Int>,
) {
    constructor() : this(docId = "0", likedProducts = listOf(1), cartProducts = listOf(1)) {}

    companion object {
        fun toObject(doc: DocumentSnapshot): User {
            val item = doc.toObject<User>() ?: throw Error("Cannot create User object")
            item.docId = doc.id
            return item
        }
    }
}