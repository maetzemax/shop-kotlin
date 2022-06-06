package com.maetzedev.shop_kotlin.screens.search

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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.maetzedev.shop_kotlin.R
import com.maetzedev.shop_kotlin.models.product.Product
import com.maetzedev.shop_kotlin.screens.destinations.ProductOverViewDestination
import com.maetzedev.shop_kotlin.utils.Formatters
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun SearchViewListRow(product: Product, navigator: DestinationsNavigator) {
    val currencyFormatter = Formatters.CurrencyFormatter()

    val fontColor = MaterialTheme.colors.onBackground

    Row(
        Modifier.fillMaxWidth()
    ) {

        Box(contentAlignment = Alignment.TopEnd) {

            Column(
                Modifier
                    .requiredHeightIn(max = 350.dp)
                    .clip(RoundedCornerShape(size = 20.dp))
                    .background(Color.White)
                    .clickable { navigator.navigate(ProductOverViewDestination(product)) }
                    .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    product.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(1.70f)
                        .requiredHeightIn(max = 200.dp),
                    placeholder = painterResource(id = R.drawable.car_placeholder)
                )

                Spacer(Modifier.height(60.dp))

                Row {
                    Row(
                        verticalAlignment = Alignment.Top, modifier = Modifier
                            .requiredHeight(140.dp)
                            .padding(10.dp)
                    ) {

                        Column() {
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
                                    fontWeight = FontWeight.Light,
                                    modifier = Modifier.padding(bottom = 10.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                }
            }



            Row {
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
    }
}