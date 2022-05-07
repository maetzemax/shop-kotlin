package com.maetzedev.shop_kotlin.screens.product

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
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
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ProductOverView(
    product: Product
) {
    Column() {
        Image(
            painterResource(id = R.drawable.car_placeholder),
            contentDescription = null,
            contentScale = FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )

        Row {
            Text(
                product.name,
                style = MaterialTheme.typography.h4
            )
            
            Spacer(modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp)
            )
            
            Text(
                product.price,
                style = MaterialTheme.typography.h4
            )
        }

        Text(product.description)

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
fun ProductOverView_Preview() {
    ProductOverView(
        product = Product(
            id = "1",
            price = "100.99",
            description = "testbeschreibung",
            name = "testname",
            seller = "Maetzi",
            created = Timestamp.now()
        )
    )
}