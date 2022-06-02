package com.maetzedev.shop_kotlin.screens.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maetzedev.shop_kotlin.models.status.Resource
import com.maetzedev.shop_kotlin.models.status.Status
import com.maetzedev.shop_kotlin.screens.product.ProductList
import com.maetzedev.shop_kotlin.uicomponents.compose.BottomBar
import com.maetzedev.shop_kotlin.uicomponents.compose.TopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun Favorite(
    favoriteViewModel: FavoriteViewModel = FavoriteViewModel(),
    navigator: DestinationsNavigator
) {
    val userData by favoriteViewModel.userData.collectAsState(initial = Resource.loading(null))

    when (userData.status) {
        Status.ERROR -> {
            Text("Es ist ein Fehler aufgetreten")
        }
        Status.LOADING -> {
            Text("Loading.....")
        }
        else -> {
            Scaffold(
                topBar = { TopBar("Gefällt dir", navigator) },
                bottomBar = { BottomBar(navigator) },

                ) {
                Column(
                    Modifier
                        .padding(bottom = it.calculateBottomPadding(), top = 20.dp)
                        .padding(horizontal = 10.dp)
                ) {
                    if (userData.data!!.likedProducts.isEmpty()) {
                        Text("Du hast keine gemerkten Produkte")
                    } else {
                        val likedProducts =
                            favoriteViewModel.getLikedProductsList(userData.data!!.likedProducts)
                                .collectAsState(
                                    initial = Resource.loading(null)
                                )
                        val products = likedProducts.value.data ?: emptyList()
                        // updates isLiked to true, because all products in favorite are already liked
                        val mappedProducts = favoriteViewModel.mapLikedProducts(products)

                        ProductList(
                            products = mappedProducts,
                            navigator = navigator
                        )
                    }
                }
            }


        }
    }
}