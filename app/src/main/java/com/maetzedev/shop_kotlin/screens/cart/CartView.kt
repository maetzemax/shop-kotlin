package com.maetzedev.shop_kotlin.screens.cart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.maetzedev.shop_kotlin.uicomponents.compose.BottomBar
import com.maetzedev.shop_kotlin.uicomponents.compose.TopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

class CartViewModel {

}

@Destination
@Composable
fun CartView(viewModel: CartViewModel = CartViewModel(), navigator: DestinationsNavigator) {

    Scaffold(
        topBar = { TopBar(title = "Warenkorb", navigator = navigator) },
        bottomBar = { BottomBar(navigator = navigator) }
    ) {
        Box(
            Modifier.padding(bottom = it.calculateBottomPadding()).fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Es befindet sich kein Produkt im Warenkorb")
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