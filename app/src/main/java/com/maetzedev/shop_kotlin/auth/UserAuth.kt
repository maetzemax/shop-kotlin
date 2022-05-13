package com.maetzedev.shop_kotlin.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.maetzedev.shop_kotlin.screens.destinations.HomeScreenDestination
import com.maetzedev.shop_kotlin.screens.destinations.LoginScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * UserAuth
 * contains functions to interact with firebase
 */
class UserAuth : UserCheck() {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    // TODO: check if there is a better way to set the displayName directly in the register process
    @Throws(UserNotLoggedIn::class)
    fun updateDisplayName(newDisplayName: String) {
        val user = Firebase.auth.currentUser ?: throw UserNotLoggedIn()

        val profileUpdates = userProfileChangeRequest {
            displayName = newDisplayName
        }

        user.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                Log.d("ProfileUpdate", task.isSuccessful.toString())
            }
    }

    @Throws(RegisterFailed::class)
    fun register(email: String, password: String, displayName: String, onSuccess: () -> Unit) {
        checkRegisterRequirements(email, password, password)
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Registration", "User successfully registered")
                    updateDisplayName(displayName)
                    onSuccess()
                } else if (task.isCanceled) {
                    throw RegisterFailed("Register failed: ${task.result}")
                }
                Log.d("Registration", task.result.toString())
            }
    }

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        checkLoginRequirements(email, password)
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Login", "User successfully logged in")
                    onSuccess()
                } else if (task.isCanceled) {
                    throw LoginFailed()
                }
            }
    }

    fun loginOnStartup(navigator: DestinationsNavigator) {
        val user = auth.currentUser

        if (user == null) {
            navigator.navigate(LoginScreenDestination)
        } else {
            navigator.navigate(HomeScreenDestination)
        }
    }

    fun passwordReset(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Passwortreset", "Email sent")
                }
                Log.d("Passwortreset", task.exception.toString())
            }
    }
}