package com.maetzedev.shop_kotlin.auth

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.rpc.context.AttributeContext
import com.maetzedev.shop_kotlin.models.user.User
import com.maetzedev.shop_kotlin.screens.destinations.HomeScreenDestination
import com.maetzedev.shop_kotlin.screens.destinations.LoginScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.sql.Timestamp
import java.time.Instant

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

    fun createDocument() {
        val uid = Firebase.auth.currentUser?.uid ?: throw Error()
        val db = Firebase.firestore

        db.collection("users").document(uid).set(User("" + System.currentTimeMillis(), listOf(0)))
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
                    createDocument()
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
}