package com.maetzedev.shop_kotlin.screens.startup

import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.maetzedev.shop_kotlin.auth.UserAuth
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * StartupScreenViewModel
 * keeps all ui logic used in the StartupScreen
 */
class StartupScreenViewModel(private val userAuth: UserAuth = UserAuth()) {

    fun loginUser(navigator: DestinationsNavigator) {
        userAuth.loginOnStartup(navigator)
    }
}