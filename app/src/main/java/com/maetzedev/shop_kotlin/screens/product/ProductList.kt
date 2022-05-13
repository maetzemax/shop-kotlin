package com.maetzedev.shop_kotlin.screens.product

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.maetzedev.shop_kotlin.models.product.Product
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ProductList(products: List<Product?>, navigator: DestinationsNavigator) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        items(products) { product ->
            if (product != null) { // If there is a product create list
                ProductListRow(product = product, navigator = navigator)
            } else { // Display error message
                // TODO: Show proper error message
                Text("Keine Ergebnisse")
            }
        }
    }
}