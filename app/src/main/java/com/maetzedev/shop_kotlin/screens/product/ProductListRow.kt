package com.maetzedev.shop_kotlin.screens.product

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import com.maetzedev.shop_kotlin.R
import com.maetzedev.shop_kotlin.models.product.Product
import com.maetzedev.shop_kotlin.screens.destinations.ProductOverViewDestination
import com.maetzedev.shop_kotlin.ui.theme.ShopkotlinTheme
import com.maetzedev.shop_kotlin.utils.Formatters
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator


@Destination
@Composable
fun ProductListRow(product: Product, navigator: DestinationsNavigator) {

    val currencyFormatter = Formatters.CurrencyFormatter()

    val fontColor = MaterialTheme.colors.onBackground

    Box(contentAlignment = Alignment.TopEnd) {
        Column(
            Modifier
                .requiredHeightIn(max = 250.dp)
                .requiredWidthIn(max=200.dp)
                .clip(RoundedCornerShape(size = 20.dp))
                .background(Color.White)
                .clickable { navigator.navigate(ProductOverViewDestination(product = product)) }
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                painterResource(id = R.drawable.car_placeholder),
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(1.70f)

            )

            Spacer(Modifier.height(5.dp))

            Row {
                Column(
                    horizontalAlignment = Alignment.Start, modifier = Modifier
                        .requiredHeight(100.dp)
                        .padding(5.dp)
                ) {
                    Text(
                        product.name,
                        style = MaterialTheme.typography.subtitle1,
                        color = fontColor,
                        maxLines = 2,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            product.description,
                            style = MaterialTheme.typography.subtitle2,
                            color = fontColor,
                            maxLines = 3,
                            fontWeight = FontWeight.Light
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

            }
        }

        Text(
            currencyFormatter.formatFloatToString(product.price),
            modifier = Modifier
                .padding(top = 15.dp)
                .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                .background(Color(0xFF243156))
                .padding(horizontal = 10.dp)
                .padding(end = 20.dp),
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.h6
        )
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
fun ProductList_Preview() {
    ShopkotlinTheme {
        ProductList(
            products = listOf(
                Product(
                    docId = "1",
                    id = 1,
                    price = 100.99,
                    description = "testbeschreibung",
                    name = "testname",
                    seller = "Maetzi",
                    created = Timestamp.now()
                ),
                Product(
                    docId = "2",
                    id = 2,
                    price = 100.99,
                    description = "testbeschreibung",
                    name = "testname",
                    seller = "Maetzi",
                    created = Timestamp.now()
                ),
                Product(
                    docId = "3",
                    id = 3,
                    price = 100.99,
                    description = "testbeschreibung",
                    name = "testname",
                    seller = "Maetzi",
                    created = Timestamp.now()
                ),
                Product(
                    docId = "4",
                    id = 4,
                    price = 100.99,
                    description = "testbeschreibung",
                    name = "testname",
                    seller = "Maetzi",
                    created = Timestamp.now()
                )
            ),
            navigator = EmptyDestinationsNavigator
        )
    }
}