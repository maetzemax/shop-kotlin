package com.maetzedev.shop_kotlin.screens.settings

import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.maetzedev.shop_kotlin.auth.UserAuth
import com.maetzedev.shop_kotlin.screens.destinations.LoginScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlin.Exception

class SettingsScreenViewModel(private val userAuth: UserAuth = UserAuth()) {

    val displayName = userAuth.auth.currentUser?.displayName
    val email = userAuth.auth.currentUser?.email

    fun onClickLogout(navigator: DestinationsNavigator) {
        userAuth.logout(navigator)
    }

    fun handleOnEmailChange(
        value: String,
        setEmail: (String) -> Unit,
        error: ErrorState,
        setError: (ErrorState) -> Unit
    ) {
        setEmail(value)
        setError(
            ErrorState(
                displayName = error.displayName,
                email = "",
                password = error.password,
                general = error.general
            )
        )

        try {
            if (value.isNotEmpty()) {
                userAuth.checkEmail(value)
            }
        } catch (e: Exception) {
            ErrorState(
                displayName = error.displayName,
                email = e.message.toString(),
                password = error.password,
                general = error.general
            )
        }
    }

    fun handleOnPasswordChange(
        value: String,
        setPassword: (String) -> Unit,
        error: ErrorState,
        setError: (ErrorState) -> Unit
    ) {
        setPassword(value)
        setError(
            ErrorState(
                general = error.general,
                email = error.general,
                password = "",
                displayName = error.displayName
            )
        )
        try {
            userAuth.checkPassword(value)
        } catch (e: Exception) {
            setError(
                ErrorState(
                    general = error.general,
                    email = error.email,
                    password = e.message.toString(),
                    displayName = error.displayName
                )
            )
        }
    }

    fun updateDisplayName(
        displayName: String,
        error: ErrorState,
        setError: (ErrorState) -> Unit,
        navigator: DestinationsNavigator
    ) {
        userAuth.updateDisplayName(displayName) { task ->
            try {
                throw task.exception!!
            } catch (e: Exception) {
                setError(
                    ErrorState(
                        displayName = e.message.toString(),
                        email = error.email,
                        password = error.password,
                        general = error.general
                    )
                )
            }
        }
        navigator.navigate(LoginScreenDestination)
    }

    fun updateEmail(
        email: String,
        error: ErrorState,
        setError: (ErrorState) -> Unit,
        navigator: DestinationsNavigator
    ) {
        try {
            userAuth.updateEmail(email) { task ->
                try {
                    throw task.exception!!
                } catch (e: FirebaseAuthRecentLoginRequiredException) {
                    navigator.navigate(LoginScreenDestination)
                } catch (e: FirebaseAuthUserCollisionException) {
                    setError(
                        ErrorState(
                            email = error.email,
                            displayName = error.displayName,
                            general = e.message.toString(),
                            password = error.password
                        )
                    )
                } catch (e: Exception) {
                    setError(
                        ErrorState(
                            email = error.email,
                            displayName = error.displayName,
                            general = "Fehler beim Ändern der Daten",
                            password = error.password
                        )
                    )
                }
            }
        } catch (e: Exception) {
            setError(
                ErrorState(
                    email = error.email,
                    displayName = error.displayName,
                    general = e.message.toString(),
                    password = error.password
                )
            )
        }
        navigator.navigate(LoginScreenDestination)
    }

    fun updatePassword(
        password: String,
        error: ErrorState,
        setError: (ErrorState) -> Unit,
        navigator: DestinationsNavigator
    ) {
        try {
            userAuth.updatePassword(
                password,
                onError = { task ->
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthRecentLoginRequiredException) {
                        navigator.navigate(LoginScreenDestination)
                    } catch (e: Exception) {
                        setError(
                            ErrorState(
                                email = error.email,
                                displayName = error.displayName,
                                general = error.general,
                                password = e.message.toString()
                            )
                        )
                    }
                })
        } catch (e: Exception) {
            setError(
                ErrorState(
                    password = error.password,
                    general = e.message.toString(),
                    email = error.email,
                    displayName = error.displayName
                )
            )
        }
        navigator.navigate(LoginScreenDestination)
    }

    fun abort(
        setEditMode: (EditState) -> Unit,
        setDisplayName: (String) -> Unit,
        setEmail: (String) -> Unit,
        setPassword: (String) -> Unit
    ) {
        setEditMode(
            EditState(displayName = false, password = false, email = false)
        )
        setDisplayName("")
        setEmail("")
        setPassword("")
    }
}