package com.maetzedev.shop_kotlin.screens.auth.login

import com.maetzedev.shop_kotlin.auth.UserAuth
import java.lang.Exception

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

    fun onClickLogin(email: String, password: String) {
        // execute login function and send user to home screen
    }

    fun onClickRegister() {
        // send user to register screen
    }

    fun onClickPasswordReset() {
        // send user to password reset screen
    }
}