package com.maetzedev.shop_kotlin.screens.auth.register

import com.maetzedev.shop_kotlin.auth.UserAuth
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.lang.Exception

/**
 * RegisterScreenViewModel
 * keeps all the ui logic used in the RegisterScreen
 */
class RegisterScreenViewModel(private val userAuth: UserAuth = UserAuth()) {

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

    fun handleOnPasswordConfirmationChange(
        value: String,
        password: String,
        setPasswordConfirmation: (String) -> Unit,
        setPasswordConfirmationError: (String) -> Unit
    ) {
        setPasswordConfirmation(value)
        setPasswordConfirmationError("")
        try {
            userAuth.checkPasswordConfirmation(password, value)
        } catch (e: Exception) {
            setPasswordConfirmationError(e.message.toString())
        }
    }

    fun onClickLogin(navigator: DestinationsNavigator) {
        navigator.navigate("login")
    }

    fun onClickRegister(email: String, password: String, displayName: String, setGeneralError: (String) -> Unit, navigator: DestinationsNavigator) {
        try {
            userAuth.register(email, password, displayName) {
                // TODO: send user to home route
            }
        } catch (e: Exception) {
            setGeneralError(e.message.toString())
        }
    }
}