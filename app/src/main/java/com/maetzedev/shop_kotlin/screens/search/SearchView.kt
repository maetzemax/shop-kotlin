package com.maetzedev.shop_kotlin.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maetzedev.shop_kotlin.models.status.Resource
import com.maetzedev.shop_kotlin.models.status.Status
import com.maetzedev.shop_kotlin.uicomponents.compose.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SearchView(navigator: DestinationsNavigator, viewModel: SearchViewModel = SearchViewModel()) {
    val (query, setQuery) = remember { mutableStateOf("") }
    val products by viewModel.products.collectAsState(initial = Resource.loading(null))
    val userData by viewModel.userData.collectAsState(initial = Resource.loading(null))

    Scaffold(
        topBar = { TopBar(title = "Suche", navigator = navigator) },
        bottomBar = { BottomBar(navigator = navigator) }
    ) {
        Container {

            when (products.status) {
                Status.ERROR -> {
                    CenteredBoxWithText(
                        text = "Leider ist ein Fehler aufgetreten",
                        paddingValues = it
                    )
                }

                Status.LOADING -> {
                    CenteredBoxWithText(text = "Loading...", paddingValues = it)
                }

                Status.SUCCESS -> {
                    if (products.data!!.isNotEmpty() && userData.status == Status.SUCCESS) {
                        Column(Modifier.padding(bottom = it.calculateBottomPadding())) {
                            InputField(
                                value = query,
                                onValueChange = { queryValue -> setQuery(queryValue) },
                                placeHolder = "Deine Suche",
                            )

                            Spacer(Modifier.size(10.dp))

                            val mappedProducts = viewModel.mapProducts(
                                products.data!!,
                                likedProducts = userData.data!!.likedProducts,
                                productsCart = userData.data!!.cartProducts
                            )
                            val filteredProducts = viewModel.filterByQuery(query, mappedProducts)

                            SearchListView(filteredProducts, navigator)
                        }
                    } else {
                        CenteredBoxWithText(
                            text = "Es ist leider ein unerwarteter Fehler aufgetreten.",
                            paddingValues = it
                        )
                    }
                }
            }
        }
    }
}