package com.maetzedev.shop_kotlin.screens.product

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale.Companion.FillWidth
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import com.maetzedev.shop_kotlin.R
import com.maetzedev.shop_kotlin.models.product.Product
import com.maetzedev.shop_kotlin.ui.theme.ShopkotlinTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Destination
@Composable
fun ProductListRow(product: Product, navigator: DestinationsNavigator) {

    var sizeImage by remember { mutableStateOf(IntSize.Zero) }

    val background = Color.Black
    val fontColor = Color.White

    Scaffold {
        Card(
            shape = RectangleShape,
            elevation = 4.dp,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = it.calculateBottomPadding())
                .clickable { /*navigator.navigate(ProductOverViewDestination(product = product)) */ }
                .onGloballyPositioned { layout ->
                    sizeImage = layout.size
                }
        ) {
            Box(
                contentAlignment = BottomStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(300.dp)
                    .background(Color.White)
            ) {
                Image(
                    painter = painterResource(R.drawable.placeholder_car),
                    contentDescription = "Car",
                    contentScale = FillWidth,
                    modifier = Modifier
                        .fillMaxSize()
                        .drawWithCache {
                            val gradient = Brush.verticalGradient(
                                colors = listOf(
                                    Color.White,
                                    background
                                ),
                                startY = sizeImage.height.toFloat() / 3,  // 1/3
                                endY = sizeImage.height.toFloat()
                            )
                            onDrawWithContent {
                                drawContent()
                                drawRect(gradient, blendMode = BlendMode.Multiply)
                            }
                        }

                )

                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(
                        product.name,
                        style = MaterialTheme.typography.h4,
                        color = fontColor
                    )
                    Text(
                        product.price,
                        style = MaterialTheme.typography.h5,
                        color = fontColor
                    )
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
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ProductListRow_Preview() {
    ShopkotlinTheme {
        ProductListRow(
            product = Product(
                id = "1",
                price = "100.99",
                description = "testbeschreibung",
                name = "testname",
                seller = "Maetzi",
                created = Timestamp.now()
            ),
            navigator = EmptyDestinationsNavigator
        )
    }
}