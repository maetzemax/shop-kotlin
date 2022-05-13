package com.maetzedev.shop_kotlin.screens.auth.login

import com.maetzedev.shop_kotlin.auth.UserAuth
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.lang.Exception

/**
 * LoginScreenViewModel
 * keeps all the ui logic used in the LoginScreen
 */
class LoginScreenViewModel(private val userAuth: UserAuth = UserAuth()) {

    fun handleOnEmailChange(
        value: String,
        setEmail: (String) -> Unit,
        setEmailError: (String) -> Unit
    ) {
        setEmail(value)
        setEmailError("")

        try {
            if (value.isNotEmpty()) {
                userAuth.checkEmail(value)
            }
        } catch (e: Exception) {
            setEmailError(e.message.toString())
        }
    }

    fun handleOnPasswordChange(
        value: String,
        setPassword: (String) -> Unit,
        setPasswordError: (String) -> Unit
    ) {
        setPassword(value)
        setPasswordError("")

        try {
            userAuth.checkPassword(value)
        } catch (e: Exception) {
            setPasswordError(e.message.toString())
        }
    }

    fun onClickLogin(email: String, password: String, setGeneralError: (String) -> Unit, navigator: DestinationsNavigator) {
        try {
            userAuth.login(email, password) {
                // TODO: send user to home screen
            }
        } catch (e: Exception) {
            setGeneralError(e.message.toString())
        }
    }

    fun onClickRegister(navigator: DestinationsNavigator) {
        navigator.navigate("register")
    }

    fun onClickPasswordReset(navigator: DestinationsNavigator) {
        navigator.navigate("passwordreset")
    }
}