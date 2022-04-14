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
import com.maetzedev.shop_kotlin.ui.atoms.Container
import com.maetzedev.shop_kotlin.ui.atoms.InputField
import com.maetzedev.shop_kotlin.ui.atoms.ScreenHeadline

@Composable
fun RegisterScreen(onClickRegister: () -> Unit, onClickLogin: () -> Unit) {
    val (email, setEmail) = remember { mutableStateOf("") }
    val (userName, setUserName) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (passwordConfirmation, setPasswordConfirmation) = remember { mutableStateOf("") }

    Container() {
        ScreenHeadline(text = "Registrierung")
        Spacer(modifier = Modifier.size(100.dp))
        InputField(
            value = email,
            onValueChange = { setEmail(it) },
            label = "E-Mail Adresse",
            placeHolder = "example@website.com",
            isEmail = true
        )
        Spacer(modifier = Modifier.size(20.dp))
        InputField(
            value = userName,
            onValueChange = { setUserName(it) },
            label = "Nutzername",
            placeHolder = "mustername"
        )
        Spacer(modifier = Modifier.size(20.dp))
        InputField(
            value = password,
            onValueChange = { setPassword(it) },
            label = "Passwort",
            placeHolder = "Passwort",
            hintText = "Das Passwort muss aus mind. 8 Zeichen bestehen"
        )
        InputField(
            value = passwordConfirmation,
            onValueChange = { setPasswordConfirmation(it) },
            placeHolder = "Passwort wiederholen"
        )
        Spacer(modifier = Modifier.size(20.dp))
        Button(
            onClick = onClickRegister,
            modifier = Modifier.fillMaxWidth(),
            shape = CircleShape
        ) {
            Text(text = "Registrieren")
        }

        Divider()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Bereits ein Konto?",
                color = Color.Gray
            )

            TextButton(
                onClick = onClickLogin,
            ) {
                Text("Anmelden")
            }
        }
    }
}