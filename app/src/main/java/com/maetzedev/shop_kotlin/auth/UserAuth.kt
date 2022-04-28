package com.maetzedev.shop_kotlin.auth

import android.text.TextUtils
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

/**
 * UserAuth
 * contains functions to interact with firebase
 */
class UserAuth {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

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

    @Throws(PasswordTooShort::class, PasswordsDontMatch::class)
    fun checkRegisterRequirements(email: String, password: String, passwordConfirmation: String) {
        // Password and passwordConfirmation must match
        checkPasswordConfirmation(password, passwordConfirmation)

        // Password must contain at least 8 chars
        checkPassword(password)
    }

    @Throws(RegisterFailed::class)
    fun register(email: String, password: String) {
        checkRegisterRequirements(email, password, password)
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Registration", "User successfully registered")
                } else if (task.isCanceled) {
                    throw RegisterFailed("Register failed: ${task.result}")
                }
                Log.d("Registration", task.result.toString())
            }
    }
}