package com.maetzedev.shop_kotlin.screens.product

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
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

@Destination(start = true) // only for building the project
@Composable
fun ProductList(products: List<Product?>, paddingValues: PaddingValues, navigator: DestinationsNavigator) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(
                top = 20.dp,
                bottom = paddingValues.calculateBottomPadding()
            )
    )
    {
        items(products) { product ->
            if (product != null) {
                ProductListRow(product = product, navigator = navigator)
            } else {
                Text("Keine Ergebnisse")
            }
        }
    }
}