package com.maetzedev.shop_kotlin.screens.settings

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maetzedev.shop_kotlin.uicomponents.compose.Container
import com.maetzedev.shop_kotlin.uicomponents.compose.ScreenHeadline
import com.maetzedev.shop_kotlin.uicomponents.compose.buttons.PrimaryButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Destination
@Composable
fun SettingsScreen(
    settingsScreenViewModel: SettingsScreenViewModel? = SettingsScreenViewModel(),
    navigator: DestinationsNavigator
) {
    Container {
        ScreenHeadline("Einstellungen")

        Spacer(Modifier.size(20.dp))

        PrimaryButton({
            Text("Logout")
        }) {
            settingsScreenViewModel?.onClickLogout(navigator)
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
fun PreviewSettingsScreen() {
    SettingsScreen(null, EmptyDestinationsNavigator)
}