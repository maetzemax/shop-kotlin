package com.maetzedev.shop_kotlin.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.FirebaseApp
import com.google.firebase.Timestamp
import com.google.firebase.ktx.Firebase
import com.maetzedev.shop_kotlin.models.product.Product
import com.maetzedev.shop_kotlin.models.status.Resource
import com.maetzedev.shop_kotlin.models.status.Status
import com.maetzedev.shop_kotlin.ui.theme.ShopkotlinTheme
import com.maetzedev.shop_kotlin.uicomponents.component.BottomBar
import com.maetzedev.shop_kotlin.uicomponents.component.TopBar
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = HomeScreenViewModel()) {

    val listState by viewModel.currentListState.collectAsState(initial = Resource.loading(null))
    val products = listState.data ?: emptyList()

    if (listState.status == Status.ERROR) {
        Text("Error: ${listState.message}")
    }
    else if (listState.status == Status.LOADING) {
        Text("Loading ....")
    }
    else {
        Scaffold(
            topBar = { TopBar("Home") },
            bottomBar = { BottomBar() }
        ) {
            ProductList(products = products, paddingValues = it )
        }
    }
}

@Composable
fun ProductList(products: List<Product?>, paddingValues: PaddingValues) {
    LazyColumn(Modifier.padding(20.dp)) {
        items(products) { product ->
            if (product != null) {
                Card(
                    shape = RectangleShape,
                    elevation = 4.dp
                ) {
                    Column {
                        
                        Text(product.name)
                        Text(product.price)
                    }

                }
            } else {
                Text("Keine Ergebnisse")
            }
        }
    }
}

@Preview(
    name = "LightMode",
    showBackground = true,
    showSystemUi = true
)
@Preview(
    name = "DarkMode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HomeScreenPreview() {
    ProductList(products = listOf(Product(id = "1", price = "100.99", description = "testbeschreibung", name = "testname", seller = "Maetzi", created = Timestamp.now())), paddingValues = PaddingValues())
}