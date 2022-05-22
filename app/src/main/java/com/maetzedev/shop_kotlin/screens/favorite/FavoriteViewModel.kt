package com.maetzedev.shop_kotlin.screens.favorite

import android.util.Log
import com.google.firebase.FirebaseApp
import com.maetzedev.shop_kotlin.models.product.Product
import com.maetzedev.shop_kotlin.models.status.Resource
import com.maetzedev.shop_kotlin.repositories.ProductsRepo
import com.maetzedev.shop_kotlin.repositories.UserRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

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


}