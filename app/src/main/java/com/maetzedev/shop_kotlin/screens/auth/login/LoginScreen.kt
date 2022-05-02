package com.maetzedev.shop_kotlin.screens.auth.login

import android.content.res.Configuration
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maetzedev.shop_kotlin.uicomponents.compose.Container
import com.maetzedev.shop_kotlin.uicomponents.compose.InputField
import com.maetzedev.shop_kotlin.uicomponents.compose.ScreenHeadline

@Composable
fun LoginScreen(loginScreenViewModel: LoginScreenViewModel = LoginScreenViewModel()) {

    val (email, setEmail) = remember { mutableStateOf("") }
    val (emailError, setEmailError) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (passwordError, setPasswordError) = remember { mutableStateOf("") }

    Container {

        Column {

            ScreenHeadline("Anmeldung")

            Spacer(Modifier.size(100.dp))

            InputField(
                email,
                { loginScreenViewModel.handleOnEmailChange(email, setEmail, setEmailError) },
                "E-Mail Adresse",
                "example@website.com",
                isEmail = true,
                errorText = emailError
            )

            Spacer(Modifier.size(20.dp))

            InputField(
                password,
                {
                    loginScreenViewModel.handleOnPasswordChange(
                        password,
                        setPassword,
                        setPasswordError
                    )
                },
                "Passwort",
                "Passwort",
                isPassword = true,
                errorText = passwordError
            )

            Spacer(Modifier.size(20.dp))

            Button(
                { loginScreenViewModel.onClickLogin(email, password) },
                Modifier.fillMaxWidth(),
                shape = CircleShape
            ) {
                Text("Anmelden")
            }

            Spacer(Modifier.size(20.dp))

            TextButton(
                { loginScreenViewModel.onClickPasswordReset() },
                Modifier.fillMaxWidth()
            ) {
                Text("Passwort vergessen?")
            }

            Divider()

            Spacer(Modifier.size(20.dp))

            Column(
                Modifier
                    .fillMaxWidth(),
                Arrangement.Center,
                Alignment.CenterHorizontally,
            ) {

                Text(
                    "Noch kein Konto?",
                    color = Color.Gray
                )

                TextButton({ loginScreenViewModel.onClickRegister() }) {
                    Text("Registrieren")
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}