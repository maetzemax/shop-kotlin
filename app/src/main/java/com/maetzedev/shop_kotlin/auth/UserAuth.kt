package com.maetzedev.shop_kotlin.auth

import android.text.TextUtils
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.google.rpc.context.AttributeContext

/**
 * UserAuth
 * contains functions to interact with firebase
 */
class UserAuth {
    private lateinit var auth: FirebaseAuth

    private fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    @Throws(EmailNotValid::class)
    fun checkEmail(email: String) {
        if (!email.isEmailValid()) {
           throw EmailNotValid()
        }
    }

    @Throws(PasswordTooShort::class)
    fun checkPassword(password: String) {
        if (password.length < 8) {
            throw PasswordTooShort()
        }
    }

    @Throws(PasswordsDontMatch::class)
    fun checkPasswordConfirmation(password: String, passwordConfirmation: String) {
        if (password != passwordConfirmation) {
            throw PasswordsDontMatch()
        }
    }

    @Throws(PasswordTooShort::class, PasswordsDontMatch::class, EmailNotValid::class)
    fun checkRegisterRequirements(email: String, password: String, passwordConfirmation: String) {
        checkEmail(email)

        // Password and passwordConfirmation must match
        checkPasswordConfirmation(password, passwordConfirmation)

        // Password must contain at least 8 chars
        checkPassword(password)
    }

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
    fun register(email: String, password: String, displayName: String) {
        checkRegisterRequirements(email, password, password)
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Registration", "User successfully registered")
                    updateDisplayName(displayName)
                } else if (task.isCanceled) {
                    throw RegisterFailed("Register failed: ${task.result}")
                }
                Log.d("Registration", task.result.toString())
            }
    }
}