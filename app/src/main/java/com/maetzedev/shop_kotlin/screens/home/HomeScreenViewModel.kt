package com.maetzedev.shop_kotlin.screens.home

import com.maetzedev.shop_kotlin.models.product.Product

class HomeScreenViewModel {

    var productList: List<Product> = listOf(
        Product(
            id = 0,
            name = "T-Shirt",
            description = "Ein weißes T-Shirt ohne Print, bestehend aus 100% Baumwolle.",
            price = 12.99,
            seller = "maetzi"
        )
    )

}