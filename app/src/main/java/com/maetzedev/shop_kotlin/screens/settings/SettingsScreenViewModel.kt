package com.maetzedev.shop_kotlin.screens.settings

import com.maetzedev.shop_kotlin.auth.UserAuth
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class SettingsScreenViewModel(private val userAuth: UserAuth = UserAuth()) {

    fun onClickLogout(navigator: DestinationsNavigator) {
        userAuth.logout(navigator)
    }
}