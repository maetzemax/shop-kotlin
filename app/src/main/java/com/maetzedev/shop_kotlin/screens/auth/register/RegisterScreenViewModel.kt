package com.maetzedev.shop_kotlin.screens.auth.register

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.maetzedev.shop_kotlin.auth.UserAuth
import com.maetzedev.shop_kotlin.screens.destinations.HomeScreenDestination
import com.maetzedev.shop_kotlin.screens.destinations.LoginScreenDestination
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
        navigator.navigate(LoginScreenDestination)
    }

    fun onClickRegister(
        email: String,
        password: String,
        passwordConfirmation: String,
        displayName: String,
        setGeneralError: (String) -> Unit,
        navigator: DestinationsNavigator
    ) {
        try {
            userAuth.register(email, password, passwordConfirmation, displayName,
                onSuccess = {
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
}