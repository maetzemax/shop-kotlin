package com.maetzedev.shop_kotlin.auth

import android.text.TextUtils

/**
 * UserCheck
 * contains fun to check if user data matches some standards
 */
open class UserCheck {
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

    @Throws(EmailNotValid::class, PasswordTooShort::class)
    fun checkLoginRequirements(email: String, password: String) {
        checkEmail(email)
        checkPassword(password)
    }
}