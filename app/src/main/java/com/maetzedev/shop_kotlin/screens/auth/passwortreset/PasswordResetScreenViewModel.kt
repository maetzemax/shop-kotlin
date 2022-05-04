package com.maetzedev.shop_kotlin.screens.auth.passwortreset

import com.maetzedev.shop_kotlin.auth.UserAuth
import java.lang.Exception

/**
 * PasswordResetScreenViewModel
 * keeps all the ui logic used in the RegisterScreen
 */
class PasswordResetScreenViewModel(private val userAuth: UserAuth = UserAuth()) {
    fun onClickPasswordReset(email: String) {
        userAuth.passwordReset(email)
    }

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
}