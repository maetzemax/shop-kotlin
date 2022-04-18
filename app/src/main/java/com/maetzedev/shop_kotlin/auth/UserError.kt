package com.maetzedev.shop_kotlin.auth

import java.lang.Exception

open class CustomException(message: String? = null, cause: Throwable? = null) :
    Exception(message, cause) {
    constructor(cause: Throwable) : this(null, cause)
}

class PasswordTooShort(
    message: String = "Password is too short. ",
    cause: Throwable? = null
) : CustomException(message, cause)

class PasswordsDontMatch(
    message: String = "Passwords don't match",
    cause: Throwable? = null,
) : CustomException(message, cause)

class RegisterFailed(
    message: String = "Registration failed",
    cause: Throwable? = null
) : CustomException(message, cause)
