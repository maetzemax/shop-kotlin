package com.maetzedev.shop_kotlin.screens.startup

import androidx.compose.runtime.Composable
import com.maetzedev.shop_kotlin.auth.UserAuth
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * StartupScreen
 * @param startupScreenViewModel StartupScreenViewModel
 * @param navigator DestinationsNavigator
 */
@Destination(start = true)
@Composable
fun StartupScreen(
    startupScreenViewModel: StartupScreenViewModel = StartupScreenViewModel(UserAuth()),
    navigator: DestinationsNavigator
) {

    startupScreenViewModel.loginUser(navigator)

    // TODO: add a nice loading animation when we have time for it
}

