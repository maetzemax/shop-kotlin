package com.maetzedev.shop_kotlin.screens.product

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.maetzedev.shop_kotlin.models.product.Product
import com.maetzedev.shop_kotlin.repositories.ProductsRepo

class ProductViewModel(product: Product) {

    private val productsRepo by lazy { ProductsRepo() }

    var isProductLiked by mutableStateOf(false)
        private set

    init {
        isProductLiked = product.isLiked!!
    }

    fun addToFavoriteItems(productId: Int) {
        productsRepo.addToLikedProducts(productId)
    }

    fun updateLike(product: Product) {
        isProductLiked = !isProductLiked
    }

}