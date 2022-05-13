package com.maetzedev.shop_kotlin.auth

import java.lang.Exception
import kotlin.jvm.Throws

open class CustomException(message: String? = null, cause: Throwable? = null) :
    Exception(message, cause) {
    constructor(cause: Throwable) : this(null, cause)
}

class PasswordTooShort(
    message: String = "Passwort ist zu kurz",
    cause: Throwable? = null
) : CustomException(message, cause)

class PasswordsDontMatch(
    message: String = "Passwörter stimmen nicht überein",
    cause: Throwable? = null,
) : CustomException(message, cause)

class EmailNotValid(
    message: String = "E-Mail ist nicht gültig",
    cause: Throwable? = null,
) : CustomException(message, cause)

class RegisterFailed(
    message: String = "Registrierung ist fehlgeschlagen",
    cause: Throwable? = null
) : CustomException(message, cause)

class UserNotLoggedIn(
    message: String = "Nutzer ist nicht eingeloggt",
    cause: Throwable? = null
) : CustomException(message, cause)

class LoginFailed (
    message: String = "Login ist fehlgeschlagen",
    cause: Throwable? = null
) : CustomException(message, cause)
