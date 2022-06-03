package com.maetzedev.shop_kotlin.screens.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maetzedev.shop_kotlin.models.status.Resource
import com.maetzedev.shop_kotlin.models.status.Status
import com.maetzedev.shop_kotlin.screens.product.ProductList
import com.maetzedev.shop_kotlin.ui.theme.ShopkotlinTheme
import com.maetzedev.shop_kotlin.uicomponents.compose.BottomBar
import com.maetzedev.shop_kotlin.uicomponents.compose.TopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Destination
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = HomeScreenViewModel(),
    navigator: DestinationsNavigator
) {

    val listState by viewModel.currentListState.collectAsState(initial = Resource.loading(null))
    val userData by viewModel.userData.collectAsState(initial = Resource.loading(null))
    val products = listState.data ?: emptyList()

    

    when (listState.status) {
        Status.ERROR -> { // In case of an error:
            Text("Error: ${listState.message}")
        }
        Status.LOADING -> { // Showed while the view is loading
            Text("Loading ....") // TODO: Replace with proper animation.
        }
        else -> { // Show all results fetched from firebase

            userData.data.let { data ->

                if (userData.status == Status.SUCCESS) {
                    val mappedProducts =
                        viewModel.mapLikedProducts(products, userData.data!!.likedProducts)


                    Scaffold(
                        topBar = { TopBar("vehicled", navigator) },
                        bottomBar = { BottomBar(navigator) },

                        ) {
                        LazyColumn(
                            Modifier
                                .padding(bottom = it.calculateBottomPadding(), top = 20.dp)
                                .padding(horizontal = 10.dp)
                        ) {

                            item {

                                Text("Alle Produkte")

                                ProductList(
                                    products = products,
                                    navigator = navigator
                                )

                                Spacer(Modifier.height(20.dp))

                                Text("Preis Absteigend")

                                ProductList(
                                    products = products.sortedByDescending { it!!.price },
                                    navigator = navigator
                                )

                                Spacer(Modifier.height(20.dp))

                                Text("Name A-Z")

                                ProductList(
                                    products = products.sortedBy { it!!.name },
                                    navigator = navigator
                                )

                                Spacer(Modifier.height(20.dp))
                            }
                        }
                    }
                }
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
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HomeScreenPreview() {
    ShopkotlinTheme {
        HomeScreen(navigator = EmptyDestinationsNavigator)
    }
}
