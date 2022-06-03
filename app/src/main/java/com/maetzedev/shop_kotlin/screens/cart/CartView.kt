package com.maetzedev.shop_kotlin.screens.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.runtime.*
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
        Column(Modifier.padding(it)) {
            Row {

                var amount by remember { mutableStateOf(1) }

                Text(text = "Krümmre, Anzahl:" + amount.toString())

                Button(onClick = { amount += 1 }) {
                    Icon(Icons.Rounded.AddCircle, contentDescription = null)
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