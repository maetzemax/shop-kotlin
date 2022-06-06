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


    fun mapProducts(products: List<Product?>, likedProducts: List<Int>, productsCart: List<Int>): List<Product?> {
        val mappedProducts: MutableList<Product?> = emptyList<Product>().toMutableList()
        var isLikedTemp = false
        var isInCartTemp = false

        // very weird way of mapping a product, but kotlin does not provide a better way (.map does not work)
        products.forEach() { product ->
            likedProducts.forEach {
                if (product!!.id == it) {
                    isLikedTemp = true
                }
            }
            productsCart.forEach {
                if (product!!.id == it) {
                    isInCartTemp = true
                }
            }
            mappedProducts.add(
                Product(
                    docId = product!!.docId,
                    id = product.id,
                    created = product.created,
                    name = product.name,
                    isLiked = isLikedTemp,
                    isInCart = isInCartTemp,
                    description = product.description,
                    price = product.price,
                    seller = product.seller,
                    category = product.category,
                    imageUrl = product.imageUrl
                )
            )
            isInCartTemp = false
            isLikedTemp = false
        }
        return mappedProducts
    }
}