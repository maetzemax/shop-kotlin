package com.maetzedev.shop_kotlin.screens.auth.login

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maetzedev.shop_kotlin.uicomponents.compose.*
import com.maetzedev.shop_kotlin.uicomponents.compose.buttons.PrimaryButton
import com.maetzedev.shop_kotlin.uicomponents.compose.buttons.PrimaryTextButton
import com.maetzedev.shop_kotlin.uicomponents.compose.texts.ErrorText
import com.maetzedev.shop_kotlin.uicomponents.compose.texts.GrayText
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

/**
 * LoginScreen
 * params are optional because otherwise the previews don't work
 * @param loginScreenViewModel LoginScreenViewModel - optional
 * @param navigator DestinationsNavigator - optional
 */
@Destination()
@Composable
fun LoginScreen(
    loginScreenViewModel: LoginScreenViewModel? = LoginScreenViewModel(),
    navigator: DestinationsNavigator
) {

    val (email, setEmail) = remember { mutableStateOf("") }
    val (emailError, setEmailError) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (passwordError, setPasswordError) = remember { mutableStateOf("") }
    val (generalError, setGeneralError) = remember { mutableStateOf("") }

    Container {

        Column {

            ScreenHeadline("Anmeldung")

            Spacer(Modifier.size(100.dp))

            InputField(
                email,
                { loginScreenViewModel?.handleOnEmailChange(it, setEmail, setEmailError) },
                "E-Mail Adresse",
                "example@website.com",
                isEmail = true,
                errorText = emailError
            )

            Spacer(Modifier.size(20.dp))

            InputField(
                password,
                { loginScreenViewModel?.handleOnPasswordChange(it, setPassword, setPasswordError) },
                "Passwort",
                "Passwort",
                isPassword = true,
                errorText = passwordError
            )

            Spacer(Modifier.size(20.dp))

            ErrorText(generalError)

            PrimaryButton("Anmelden") { loginScreenViewModel?.onClickLogin(email, password, setGeneralError, navigator) }

            Spacer(Modifier.size(20.dp))

            PrimaryTextButton({
                Text("Passwort vergessen?")
            }) {
                loginScreenViewModel?.onClickPasswordReset(navigator)
            }

            Divider()

            Spacer(Modifier.size(20.dp))

            Column(
                Modifier
                    .fillMaxWidth(),
                Arrangement.Center,
                Alignment.CenterHorizontally,
            ) {

                GrayText("Noch kein Konto?")

                PrimaryTextButton({
                    Text("Registrieren")
                }) {
                    loginScreenViewModel?.onClickRegister(navigator)
                }
            }
        }
    }
}

@Preview(
    name = "LightMode",
    showSystemUi = true
)
@Preview(
    name = "DarkMode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true
)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(null, EmptyDestinationsNavigator)
}