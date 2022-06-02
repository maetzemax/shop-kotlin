package com.maetzedev.shop_kotlin.auth

import android.util.Log
import androidx.compose.animation.core.exponentialDecay
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
import java.sql.Timestamp
import java.time.Instant

/**
 * UserAuth
 * contains functions to interact with firebase
 */
class UserAuth : UserCheck() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    @Throws(UserNotLoggedIn::class)
    fun updateDisplayName(newDisplayName: String, onError: (Task<Void>) -> Unit) {
        val user = Firebase.auth.currentUser ?: throw UserNotLoggedIn()

        val profileUpdates = userProfileChangeRequest {
            displayName = newDisplayName
        }

        user.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    onError(task)
                }
                Log.d("ProfileUpdate", task.isSuccessful.toString())
            }
    }

    @Throws(UserNotLoggedIn::class)
    fun updateEmail(
        newEmail: String,
        onError: (Task<Void>) -> Unit,
    ) {
        val user = auth.currentUser ?: throw UserNotLoggedIn()
        checkEmail(newEmail)

        user.updateEmail(newEmail).addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                onError(task)
            }
        }
    }

    @Throws(UserNotLoggedIn::class)
    fun updatePassword(newPassword: String, onError: (Task<Void>) -> Unit) {
        val user = auth.currentUser ?: throw UserNotLoggedIn()
        checkPassword(newPassword)

        user.updatePassword(newPassword).addOnCompleteListener{ task ->
            if (!task.isSuccessful) {
                onError(task)
            }
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
                    updateDisplayName(displayName) {}
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
