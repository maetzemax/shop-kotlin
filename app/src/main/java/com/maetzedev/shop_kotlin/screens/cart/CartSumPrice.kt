package com.maetzedev.shop_kotlin.screens.cart

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.maetzedev.shop_kotlin.models.product.Product
import com.maetzedev.shop_kotlin.screens.destinations.CheckoutViewDestination
import com.maetzedev.shop_kotlin.uicomponents.compose.buttons.PrimaryButton
import com.maetzedev.shop_kotlin.uicomponents.compose.texts.TextTable
import com.maetzedev.shop_kotlin.utils.Formatters
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun CartSumPrice(products: List<Product?>, viewModel: CartViewModel = CartViewModel(), navigator: DestinationsNavigator) {
    val sumOfAllProducts = viewModel.getSumOfAllProducts(products)
    val deliveryCosts = 6.9
    val totalSum = sumOfAllProducts + deliveryCosts

    Column(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    ) {

        TextTable(textLeft = "Artikelpreis", textRight = Formatters.CurrencyFormatter().formatFloatToString(sumOfAllProducts))

        Spacer(Modifier.size(5.dp))

        TextTable(textLeft = "Lieferkosten", textRight = Formatters.CurrencyFormatter().formatFloatToString(deliveryCosts))

        Divider()

        TextTable(textLeft = "Gesamtpreis", textRight = Formatters.CurrencyFormatter().formatFloatToString(totalSum))

        PrimaryButton("ZUR KASSE") {
            navigator.navigate(CheckoutViewDestination(sumOfAllProducts = sumOfAllProducts, deliveryCosts = deliveryCosts, totalSum = totalSum))
        }
    }
}