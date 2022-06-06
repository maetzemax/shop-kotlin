package com.maetzedev.shop_kotlin.screens.checkout

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.maetzedev.shop_kotlin.screens.destinations.HomeScreenDestination
import com.maetzedev.shop_kotlin.uicomponents.compose.Container
import com.maetzedev.shop_kotlin.uicomponents.compose.ScreenHeadline
import com.maetzedev.shop_kotlin.uicomponents.compose.buttons.PrimaryButton
import com.maetzedev.shop_kotlin.uicomponents.compose.texts.TextTable
import com.maetzedev.shop_kotlin.utils.Formatters
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.util.*
import kotlin.concurrent.schedule

@Destination
@Composable
fun CheckoutView(sumOfAllProducts: Double, deliveryCosts: Double, totalSum: Double, navigator: DestinationsNavigator) {
    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    // we can ignore the following error
    Scaffold() { _ ->
        Container {
            ScreenHeadline(text = "Kasse")

            Spacer(Modifier.size(20.dp))

            TextTable(textLeft = "Preis der Produkte", textRight = Formatters.CurrencyFormatter().formatFloatToString(sumOfAllProducts))

            Spacer(Modifier.size(20.dp))

            TextTable(textLeft = "Lieferkosten", textRight = Formatters.CurrencyFormatter().formatFloatToString(deliveryCosts))

            Spacer(Modifier.size(20.dp))

            TextTable(textLeft = "Gesamtkosten", textRight = Formatters.CurrencyFormatter().formatFloatToString(totalSum))

            Spacer(Modifier.size(40.dp))

            PrimaryButton("Bezahlen") {
                Toast.makeText(context, "Du hast erfolgreich eine Bestellung aufgegeben", Toast.LENGTH_LONG).show()

                Timer().schedule(3500) {

                    activity.runOnUiThread {
                        navigator.navigate(HomeScreenDestination())
                    }
                }
            }
        }
    }
}