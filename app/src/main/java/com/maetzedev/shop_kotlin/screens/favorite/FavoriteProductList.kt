package com.maetzedev.shop_kotlin.screens.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maetzedev.shop_kotlin.models.product.Product
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun FavoriteProductList(products: List<Product?>, navigator: DestinationsNavigator) {
    LazyColumn(
        horizontalAlignment = Alignment.Start
    )
    {
        if (products.isNotEmpty()) {
            items(products) { product ->  // If there is a product create list
                product?.let { FavoriteProductListRow(product = it, navigator = navigator) }
                Spacer(Modifier.height(20.dp))
            }
        } else { // Display error message
            // TODO: Show proper error message
            item {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Keine Ergebnisse")
                }
            }
        }
    }
}