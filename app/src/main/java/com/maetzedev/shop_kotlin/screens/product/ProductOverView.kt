package com.maetzedev.shop_kotlin.screens.product

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import com.maetzedev.shop_kotlin.R
import com.maetzedev.shop_kotlin.models.product.Product
import com.maetzedev.shop_kotlin.screens.destinations.HomeScreenDestination
import com.maetzedev.shop_kotlin.ui.theme.ShopkotlinTheme
import com.maetzedev.shop_kotlin.uicomponents.compose.BottomBar
import com.maetzedev.shop_kotlin.utils.Formatters
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Destination
@Composable
fun ProductOverView(
    product: Product,
    navigator: DestinationsNavigator
) {

    val currencyFormatter = Formatters.CurrencyFormatter()

    Scaffold(
        bottomBar = { BottomBar(navigator) }
    ) {
        Column(
            Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 40.dp)
        ) {

            Row() {

                Icon(
                    Icons.Rounded.ArrowBack,
                    "BACK",
                    Modifier.clickable { navigator.navigate(HomeScreenDestination()) }
                )

                Spacer(Modifier.weight(1f))

                if (product.isLiked) {
                    Icon(
                        Icons.Rounded.Favorite,
                        "Favorite",
                        Modifier.clickable {
                            product.addToLikedProducts(product.id)
                        },
                        Color.Red
                    )
                } else {
                    Icon(
                        Icons.Rounded.FavoriteBorder,
                        "Favorite",
                        Modifier.clickable {
                            product.addToLikedProducts(product.id)
                        },
                        Color.Gray
                    )
                }

            }


            LazyColumn(
                content = {
                    item {
                        Spacer(modifier = Modifier.height(20.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Text(
                                product.name,
                                style = MaterialTheme.typography.h4,
                                modifier = Modifier.width(200.dp)
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            Text(
                                currencyFormatter.formatFloatToString(product.price),
                                modifier = Modifier
                                    .background(Color(0xFF243156))
                                    .padding(horizontal = 5.dp),
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold,
                                style = MaterialTheme.typography.h6
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Image(
                            painterResource(id = R.drawable.car_placeholder),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Row {
                            Spacer(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 10.dp)
                            )

                        }

                        Spacer(Modifier.height(10.dp))

                        Text(
                            text = "Beschreibung",
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(Modifier.height(10.dp))

                        Text(product.description)

                        Spacer(Modifier.height(30.dp))

                        Row {
                            Spacer(Modifier.weight(1f))

                            Text(
                                modifier = Modifier
                                    .padding(bottom = it.calculateBottomPadding() + 20.dp)
                                    .background(
                                        Color(
                                            0xFF4160B4
                                        )
                                    )
                                    .clickable { /*TODO: Add to Shopping Cart*/ }
                                    .padding(10.dp),
                                text = "ZUM WARENKORB HINZUFÜGEN",
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )

                            Spacer(Modifier.weight(1f))

                        }

                    }
                }
            )
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
fun ProductOverView_Preview() {
    ShopkotlinTheme {
        ProductOverView(
            product = Product(
                docId = "1",
                id = 1,
                price = 100.99,
                description = "testbeschreibung",
                name = "testname",
                seller = "Maetzi",
                created = Timestamp.now()
            ),
            navigator = EmptyDestinationsNavigator
        )
    }
}