package com.maetzedev.shop_kotlin.screens.cart

import com.google.firebase.FirebaseApp
import com.maetzedev.shop_kotlin.models.product.Product
import com.maetzedev.shop_kotlin.models.status.Resource
import com.maetzedev.shop_kotlin.repositories.ProductsRepo
import com.maetzedev.shop_kotlin.repositories.UserRepo
import kotlinx.coroutines.flow.Flow

class CartViewModel {
    private val userRepo by lazy { UserRepo() }
    private val productsRepo by lazy { ProductsRepo() }

    val userData = userRepo.fetchUserData()


    init {
        FirebaseApp.initializeApp(FirebaseApp.getInstance().applicationContext)
    }

    fun getCartProductsList(cartProducts: List<Int>): Flow<Resource<List<Product?>>> {
        return productsRepo.fetchProductsById(cartProducts)
    }

    fun removeProductFromCart(productId: Int) {
        productsRepo.updateProductsCart(productId)
    }

    fun mapProducts(products: List<Product?>, likedProducts: List<Int>): List<Product?> {
        val mappedProducts: MutableList<Product?> = emptyList<Product>().toMutableList()
        var isLikedTemp = false

        products.forEach { product ->
            likedProducts.forEach { likedProduct ->
                if (product!!.id == likedProduct) {
                    isLikedTemp = true
                }
            }
            mappedProducts.add(
                Product(
                    docId = product!!.docId,
                    id = product.id,
                    created = product.created,
                    name = product.name,
                    isLiked = isLikedTemp,
                    isInCart = true,
                    description = product.description,
                    price = product.price,
                    seller = product.seller
                )
            )
            isLikedTemp = false
        }

        return mappedProducts
    }

    fun getSumOfAllProducts(products: List<Product?>): Double {
        var sum = 0.0

        products.forEach {
            sum += it!!.price
        }
        return sum
    }
}