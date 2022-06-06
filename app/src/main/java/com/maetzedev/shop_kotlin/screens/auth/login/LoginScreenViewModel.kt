package com.maetzedev.shop_kotlin.screens.auth.login

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.maetzedev.shop_kotlin.auth.*
import com.maetzedev.shop_kotlin.screens.destinations.HomeScreenDestination
import com.maetzedev.shop_kotlin.screens.destinations.PasswordResetScreenDestination
import com.maetzedev.shop_kotlin.screens.destinations.RegisterScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlin.Exception

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

    fun onClickLogin(
        email: String,
        password: String,
        setGeneralError: (String) -> Unit,
        navigator: DestinationsNavigator
    ) {
        try {
            userAuth.login(email, password, onSuccess = {
                navigator.navigate(HomeScreenDestination)
            },
                onError = { task ->
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        setGeneralError("Passwort ist zu schwach")
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        setGeneralError("E-Mail Adresse ist nicht valide")
                    } catch (e: FirebaseAuthUserCollisionException) {
                        setGeneralError("Nutzer existiert bereits")
                    } catch (e: Exception) {
                        setGeneralError("Fehler bei der Anmeldung")
                    }
                })
        } catch (e: Exception) {
            setGeneralError(e.message.toString())
        }
    }

    fun onClickRegister(navigator: DestinationsNavigator) {
        navigator.navigate(RegisterScreenDestination)
    }

    fun onClickPasswordReset(navigator: DestinationsNavigator) {
        navigator.navigate(PasswordResetScreenDestination)
    }
}