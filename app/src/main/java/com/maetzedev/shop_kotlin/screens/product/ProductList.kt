package com.maetzedev.shop_kotlin.screens.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
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
fun ProductList(products: List<Product?>, navigator: DestinationsNavigator) {

    Column() {

        Text("Alle Produkte")

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(
            verticalAlignment = Alignment.Top
        )
        {

            if (products.isNotEmpty()) {
                items(products) { product ->  // If there is a product create list
                    product?.let { ProductListRow(product = it, navigator = navigator) }
                    Spacer(Modifier.width(10.dp))
                }
            } else { // Display error message
                // TODO: Show proper error message
                item {
                    Text("Keine Ergebnisse")
                }
            }

        }

    }
}