package com.maetzedev.shop_kotlin.screens.product

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.FillWidth
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import com.maetzedev.shop_kotlin.R
import com.maetzedev.shop_kotlin.models.product.Product
import com.maetzedev.shop_kotlin.ui.theme.ShopkotlinTheme
import com.maetzedev.shop_kotlin.uicomponents.component.BottomBar
import com.maetzedev.shop_kotlin.uicomponents.compose.TopBar
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
        topBar = { TopBar(title = product.name, navigator) },
        bottomBar = { BottomBar() }
    ) {
        Column(
            Modifier.padding(horizontal = 20.dp)
        ) {
            Image(
                painterResource(id = R.drawable.car_placeholder),
                contentDescription = null,
                contentScale = FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
            )

            LazyColumn(
                modifier = Modifier.padding(it),
                content = {
                item {
                    Row {
                        Text(
                            product.name,
                            style = MaterialTheme.typography.h4
                        )

                        Spacer(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 10.dp)
                        )

                        Text(
                            currencyFormatter.formatFloatToString(product.price),
                            style = MaterialTheme.typography.h4
                        )
                    }

                    Text("Verkäufer: ${product.seller}")

                    Spacer(Modifier.height(20.dp))

                    Text(product.description)
                    
                    Spacer(Modifier.height(50.dp))
                    
                    Button(onClick = { /*TODO: Add to Shopping Cart*/ }) {
                        Text(text = "Zum Warenkorb hinzufügen")
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
            EmptyDestinationsNavigator
        )
    }
}