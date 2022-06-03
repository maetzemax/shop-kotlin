package com.maetzedev.shop_kotlin.screens.favorite

import com.google.firebase.FirebaseApp
import com.maetzedev.shop_kotlin.models.product.Product
import com.maetzedev.shop_kotlin.models.status.Resource
import com.maetzedev.shop_kotlin.repositories.ProductsRepo
import com.maetzedev.shop_kotlin.repositories.UserRepo
import kotlinx.coroutines.flow.Flow

class FavoriteViewModel {
    private val productsRepo by lazy { ProductsRepo() }
    private val userRepo by lazy { UserRepo() }

    val userData = this.userRepo.fetchUserData()

    init {
        FirebaseApp.initializeApp(FirebaseApp.getInstance().applicationContext)
    }

    fun getLikedProductsList(likedProducts: List<Int>): Flow<Resource<List<Product?>>> {
        return productsRepo.fetchProductsById(likedProducts)
    }

    fun mapLikedProducts(products: List<Product?>): List<Product?> {
        var mappedProducts: MutableList<Product?> = emptyList<Product>().toMutableList()
        products.map { product ->
            mappedProducts.add(Product(
                docId = product!!.docId,
                id = product.id,
                created = product.created,
                name = product.name,
                isLiked = true,
                description = product.description,
                price = product.price,
                seller = product.seller
            ))
        }

        return mappedProducts
    }

    fun addToLikedProduct(productId: Int) {
        productsRepo.addToLikedProducts(productId)
    }


}