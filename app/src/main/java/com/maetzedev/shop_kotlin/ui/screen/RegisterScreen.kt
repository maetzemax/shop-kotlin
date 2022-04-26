package com.maetzedev.shop_kotlin.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.maetzedev.shop_kotlin.auth.UserAuth
import com.maetzedev.shop_kotlin.ui.atoms.Container
import com.maetzedev.shop_kotlin.ui.atoms.InputField
import com.maetzedev.shop_kotlin.ui.atoms.ScreenHeadline
import java.lang.Exception

@Composable
fun RegisterScreen(
    onClickRegister: (email: String, password: String) -> Unit,
    onClickLogin: () -> Unit
) {
    val userAuth = UserAuth()

    val (email, setEmail) = remember { mutableStateOf("") }
    val (userName, setUserName) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (passwordConfirmation, setPasswordConfirmation) = remember { mutableStateOf("") }

    val (emailError, setEmailError) = remember { mutableStateOf("") }
    val (passwordError, setPasswordError) = remember { mutableStateOf("") }
    val (passwordConfirmationError, setPasswordConfirmationError) = remember { mutableStateOf("") }

    fun handleOnEmailChange(value: String) {
        setEmail(value)
        setEmailError("")
        try {
            if (email.isNotEmpty()) {
                userAuth.checkEmail(value)
            }
        } catch (e: Exception) {
            setEmailError(e.message.toString())
        }
    }

    fun handleOnPasswordChange(value: String) {
        setPassword(value)
        setPasswordError("")
        try {
            userAuth.checkPassword(value)
        } catch (e: Exception) {
            setPasswordError(e.message.toString())
        }
    }

    fun handleOnPasswordConfirmationChange(value: String) {
        setPasswordConfirmation(value)
        setPasswordConfirmationError("")
        try {
            userAuth.checkPasswordConfirmation(password, value)
        } catch (e: Exception) {
            setPasswordConfirmationError(e.message.toString())
        }
    }

    Container {
        Column {
            ScreenHeadline("Registrierung")
            Spacer(Modifier.size(80.dp))
            InputField(
                email,
                { handleOnEmailChange(it) },
                "E-Mail Adresse",
                "example@website.com",
                isEmail = true,
                errorText = emailError
            )
            Spacer(Modifier.size(20.dp))
            InputField(
                userName,
                { setUserName(it) },
                "Nutzername",
                "mustername"
            )
            Spacer(Modifier.size(20.dp))
            InputField(
                password,
                { handleOnPasswordChange(it) },
                "Passwort",
                "Passwort",
                "Das Passwort muss aus mind. 8 Zeichen bestehen",
                isPassword = true,
                errorText = passwordError
            )
            InputField(
                passwordConfirmation,
                { handleOnPasswordConfirmationChange(it) },
                "Passwort wiederholen",
                isPassword = true,
                errorText = passwordConfirmationError
            )
            Spacer(modifier = Modifier.size(20.dp))
            Button(
                { onClickRegister(email, password) },
                Modifier.fillMaxWidth(),
                shape = CircleShape
            ) {
                Text("Registrieren")
            }

            Divider()

            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                Arrangement.Center,
                Alignment.CenterHorizontally,
            ) {
                Text(
                    "Bereits ein Konto?",
                    color = Color.Gray
                )

                TextButton(onClickLogin) {
                    Text("Anmelden")
                }
            }
        }
    }
}