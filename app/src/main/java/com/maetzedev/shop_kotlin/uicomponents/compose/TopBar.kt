package com.maetzedev.shop_kotlin.uicomponents.component

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maetzedev.shop_kotlin.ui.theme.ShopkotlinTheme

@Composable
fun TopBar(
    title: String,
    // navigator: DestinationsNavigator
) {
    ShopkotlinTheme {
        TopAppBar {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    title,
                    style = MaterialTheme.typography.h4
                )
                Spacer(Modifier.weight(1f))

                Row {
                    Icon(
                        Icons.Rounded.Search,
                        "Search",
                        Modifier.clickable { TODO("Implementation of Navigation") }
                    )

                    Spacer(Modifier.width(10.dp))

                    Icon(
                        Icons.Rounded.Settings,
                        "Settings",
                        Modifier.clickable { TODO("Implementation of Navigation") }
                    )
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
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun TopBar_Preview() {
    Scaffold(
        topBar ={
            TopBar("Title")
        }
    ) {
        Column(Modifier.padding(it)) {
            // Empty because only the topBar is interesting.
        }
    }
}