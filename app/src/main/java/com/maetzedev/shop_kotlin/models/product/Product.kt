package com.maetzedev.shop_kotlin.models.product

/**
    Creates a product based on id, name, description, price and seller. (/D010/)
*/
data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val seller: String // TODO: Replace String with User
)