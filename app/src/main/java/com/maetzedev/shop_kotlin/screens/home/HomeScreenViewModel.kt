package com.maetzedev.shop_kotlin.screens.home


import com.google.firebase.FirebaseApp
import com.maetzedev.shop_kotlin.models.product.Product
import com.maetzedev.shop_kotlin.repositories.ProductsRepo
import com.maetzedev.shop_kotlin.repositories.UserRepo

class HomeScreenViewModel {

    private val productRepository by lazy { ProductsRepo() }
    private val userRepo by lazy { UserRepo() }

    init {
        FirebaseApp.initializeApp(FirebaseApp.getInstance().applicationContext)
    }

    var currentListState = this.productRepository.fetchProducts()
    var userData = this.userRepo.fetchUserData()

    fun mapLikedProducts(products: List<Product?>, likedProducts: List<Int>) {
        products.forEach() { product ->
            likedProducts.forEach() {
                if (product!!.id == it) {
                    product.isLiked = true
                }
            }
        }
    }
}