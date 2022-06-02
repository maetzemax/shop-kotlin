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
    

    fun mapLikedProducts(products: List<Product?>, likedProducts: List<Int>): MutableList<Product?> {
        val mappedProducts: MutableList<Product?> = listOf(null).toMutableList()
        var isAdded = false

        products.forEach() { product ->
            likedProducts.forEach() {
                if (product!!.id == it) {
                    mappedProducts.add(
                        Product(
                            docId = product.docId,
                            id = product.id,
                            created = product.created,
                            name = product.name,
                            isLiked = true,
                            description = product.description,
                            price = product.price,
                            seller = product.seller
                        )
                    )
                    isAdded = true
                }
            }
            if (!isAdded) {
                mappedProducts.add(
                    Product(
                        docId = product!!.docId,
                        id = product.id,
                        created = product.created,
                        name = product.name,
                        isLiked = false,
                        description = product.description,
                        price = product.price,
                        seller = product.seller
                    )
                )
            }
            isAdded = false
        }
        return mappedProducts
    }
}