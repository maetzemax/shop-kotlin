package com.maetzedev.shop_kotlin.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class UserAuth {
    var auth: FirebaseAuth = FirebaseAuth.getInstance()

    @Throws(PasswordTooShort::class, PasswordsDontMatch::class)
    private fun checkRegisterRequirements(email: String, password: String, passwordConfirmation: String) {
        // Password and passwordConfirmation must match
        if (password != passwordConfirmation) {
            throw PasswordsDontMatch()
        }

        // Password must contain at least 8 chars
        if (password.length < 9) {
            throw PasswordTooShort()
        }
    }

    @Throws(RegisterFailed::class)
    fun register(email: String, password: String) {
        checkRegisterRequirements(email, password, password)
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Log.d("Registration", "User successfully registered")
                } else if (task.isCanceled) {
                    throw RegisterFailed("Register failed: ${task.result}")
                }
                Log.d("Registration", task.result.toString())
            }
    }
}