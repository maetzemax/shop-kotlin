package com.maetzedev.shop_kotlin.screens.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.core.Tag
import com.maetzedev.shop_kotlin.models.product.Product
import com.maetzedev.shop_kotlin.models.status.Resource
import com.maetzedev.shop_kotlin.models.status.Status
import com.maetzedev.shop_kotlin.repositories.ProductsRepo
import kotlinx.coroutines.flow.collect
import java.util.concurrent.Flow

class HomeScreenViewModel {

    private val productRepository by lazy { ProductsRepo() }

    init {
        FirebaseApp.initializeApp(FirebaseApp.getInstance().applicationContext)
    }

    var currentListState = this.productRepository.fetchProducts()

}