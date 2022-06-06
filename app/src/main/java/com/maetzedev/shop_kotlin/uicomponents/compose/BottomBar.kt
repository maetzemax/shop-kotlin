package com.maetzedev.shop_kotlin.uicomponents.compose

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maetzedev.shop_kotlin.screens.destinations.AddProductViewDestination
import com.maetzedev.shop_kotlin.screens.destinations.CartViewDestination
import com.maetzedev.shop_kotlin.screens.destinations.FavoriteDestination
import com.maetzedev.shop_kotlin.screens.destinations.HomeScreenDestination
import com.maetzedev.shop_kotlin.ui.theme.ShopkotlinTheme
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Composable
fun BottomBar(
    navigator: DestinationsNavigator
) {
    ShopkotlinTheme {
        BottomAppBar {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Column(
                    Modifier.clickable { navigator.navigate(HomeScreenDestination) },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Rounded.Home,
                        "Home"
                    )

                    Text("Home")
                }

                Spacer(Modifier.weight(1f))

                Column(
                    Modifier.clickable { navigator.navigate(AddProductViewDestination) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        Icons.Rounded.Add,
                        "Sell"
                    )

                    Text("Anbieten")
                }

                Spacer(Modifier.weight(1f))

                Column(
                    Modifier.clickable { navigator.navigate(CartViewDestination) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        Icons.Rounded.ShoppingCart,
                        "Cart"
                    )

                    Text("Warenkorb")
                }

                Spacer(Modifier.weight(1f))

                Column(
                    Modifier.clickable { navigator.navigate(FavoriteDestination) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        Icons.Rounded.Favorite,
                        "Favorites"
                    )
                    Text("Favoriten")
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
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun BottomBar_PreviewLight() {
    Scaffold(
        bottomBar ={
            BottomBar(EmptyDestinationsNavigator)
        }
    ) {
        Column(Modifier.padding(it)) {
            // Empty because only the bottomBar is interesting.
        }
    }
}