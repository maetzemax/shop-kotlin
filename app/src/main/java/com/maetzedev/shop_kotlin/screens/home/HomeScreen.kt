package com.maetzedev.shop_kotlin.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maetzedev.shop_kotlin.models.product.Product
import com.maetzedev.shop_kotlin.uicomponents.component.BottomBar
import com.maetzedev.shop_kotlin.uicomponents.component.TopBar
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = HomeScreenViewModel()) {
    Scaffold(
        topBar = { TopBar("Home") },
        bottomBar = { BottomBar() }
    ) {
        LazyColumn { // Will be moved to external file
            items(viewModel.productList) { product: Product ->
                Column(Modifier.padding(20.dp)) {
                    Row(Modifier.fillMaxWidth()) {
                        Text(product.name)

                        Spacer(Modifier.weight(1f))

                        Text(product.price.toString() + "€")
                    }
                    Text(product.seller)
                    Text(product.description)
                }
            }
        }
    }
}

@Preview(
    name = "LightMode",
    showBackground = true,
)
@Preview(
    name = "DarkMode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}