package com.maetzedev.shop_kotlin.screens.cart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maetzedev.shop_kotlin.models.status.Resource
import com.maetzedev.shop_kotlin.models.status.Status
import com.maetzedev.shop_kotlin.uicomponents.compose.BottomBar
import com.maetzedev.shop_kotlin.uicomponents.compose.CenteredBoxWithText
import com.maetzedev.shop_kotlin.uicomponents.compose.TopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Destination
@Composable
fun CartView(viewModel: CartViewModel = CartViewModel(), navigator: DestinationsNavigator) {
    val userData by viewModel.userData.collectAsState(initial = Resource.loading(null))

    Scaffold(
        topBar = { TopBar(title = "Warenkorb", navigator = navigator) },
        bottomBar = { BottomBar(navigator = navigator) }
    ) {
        Column(
            Modifier
                .padding(bottom = it.calculateBottomPadding(), top = 20.dp)
                .padding(horizontal = 10.dp)
        ) {
            when (userData.status) {
                Status.LOADING -> {
                    CenteredBoxWithText(
                        text = "Loading...",
                        paddingValues = it
                    )
                }

                Status.ERROR -> {
                    CenteredBoxWithText(
                        text = "Leider ist ein Fehler aufgetreten",
                        paddingValues = it
                    )
                }

                Status.SUCCESS -> {
                    if (userData.data!!.cartProducts.isNotEmpty()) {
                        val products by viewModel.getCartProductsList(userData.data!!.cartProducts)
                            .collectAsState(initial = Resource.loading(null))

                        if (products.status == Status.SUCCESS && products.data!!.isNotEmpty()) {
                            val cartProducts = products.data ?: emptyList()
                            val mappedCartProducts =
                                viewModel.mapProducts(cartProducts, userData.data!!.likedProducts)

                            Column {
                                Box(Modifier.weight(4F)) {
                                    CartViewList(products = mappedCartProducts, navigator = navigator)
                                }
                                Box(Modifier.weight(1F)) {
                                    CartSumPrice(products = mappedCartProducts, navigator = navigator)
                                }
                            }
                        }
                    } else {
                        CenteredBoxWithText(
                            text = "Es befindet sich kein Produkt im Warenkorb",
                            paddingValues = it
                        )
                    }
                }
            }
        }
    }

}

@Preview(
    showSystemUi = true
)
@Composable
fun CartView_Preview() {
    CartView(navigator = EmptyDestinationsNavigator)
}