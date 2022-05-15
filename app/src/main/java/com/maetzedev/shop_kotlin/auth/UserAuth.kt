package com.maetzedev.shop_kotlin.auth

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.google.rpc.context.AttributeContext
import com.maetzedev.shop_kotlin.screens.destinations.HomeScreenDestination
import com.maetzedev.shop_kotlin.screens.destinations.LoginScreenDestination
import com.maetzedev.shop_kotlin.screens.destinations.StartupScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * UserAuth
 * contains functions to interact with firebase
 */
class UserAuth : UserCheck() {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

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

    @Throws(RegisterFailed::class, EmailAlreadyInUse::class)
    fun register(
        email: String,
        password: String,
        passwordConfirmation: String,
        displayName: String,
        onSuccess: (Task<AuthResult>) -> Unit,
        onError: (Task<AuthResult>) -> Unit
    ) {
        checkRegisterRequirements(email, password, passwordConfirmation)
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Registration", "User successfully registered")
                    updateDisplayName(displayName)
                    onSuccess(task)
                } else {
                    onError(task)
                }
            }
    }

    @Throws(LoginFailed::class)
    fun login(
        email: String,
        password: String,
        onSuccess: (task: Task<AuthResult>) -> Unit,
        onError: (Task<AuthResult>) -> Unit
    ) {
        checkLoginRequirements(email, password)
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Login", "User successfully logged in")
                    onSuccess(task)
                } else {
                    onError(task)
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

    fun logout(navigator: DestinationsNavigator) {
        auth.signOut()
        navigator.navigate(StartupScreenDestination)
    }
}