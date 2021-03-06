package com.maetzedev.shop_kotlin.screens.auth.register

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maetzedev.shop_kotlin.auth.UserAuth
import com.maetzedev.shop_kotlin.ui.theme.ShopkotlinTheme
import com.maetzedev.shop_kotlin.uicomponents.compose.Container
import com.maetzedev.shop_kotlin.uicomponents.compose.InputField
import com.maetzedev.shop_kotlin.uicomponents.compose.ScreenHeadline
import com.maetzedev.shop_kotlin.uicomponents.compose.buttons.PrimaryButton
import com.maetzedev.shop_kotlin.uicomponents.compose.texts.ErrorText
import com.maetzedev.shop_kotlin.uicomponents.compose.texts.GrayText
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

/**
 * RegisterScreen
 * params are optional because otherwise the previews don't work
 * @param registerScreenViewModel RegisterScreenViewModel - optional
 * @param navigator DestinationsNavigator - optional
 */
@Destination()
@Composable
fun RegisterScreen(
    registerScreenViewModel: RegisterScreenViewModel? = RegisterScreenViewModel(
        UserAuth()
    ),
    navigator: DestinationsNavigator
) {
    val (email, setEmail) = remember { mutableStateOf("") }
    val (displayName, setDisplayName) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (passwordConfirmation, setPasswordConfirmation) = remember { mutableStateOf("") }

    val (emailError, setEmailError) = remember { mutableStateOf("") }
    val (passwordError, setPasswordError) = remember { mutableStateOf("") }
    val (passwordConfirmationError, setPasswordConfirmationError) = remember { mutableStateOf("") }
    val (generalError, setGeneralError) = remember { mutableStateOf("") }

    ShopkotlinTheme {
        Container {

            Column {

                ScreenHeadline("Registrierung")

                Spacer(Modifier.size(80.dp))

                InputField(
                    email,
                    { registerScreenViewModel?.handleOnEmailChange(it, setEmail, setEmailError) },
                    "E-Mail Adresse",
                    "example@website.com",
                    isEmail = true,
                    errorText = emailError
                )

                Spacer(Modifier.size(20.dp))

                InputField(
                    displayName,
                    { setDisplayName(it) },
                    "Nutzername",
                    "mustername"
                )

                Spacer(Modifier.size(20.dp))

                InputField(
                    password,
                    {
                        registerScreenViewModel?.handleOnPasswordChange(
                            it,
                            setPassword,
                            setPasswordError
                        )
                    },
                    "Passwort",
                    "Passwort",
                    "Das Passwort muss aus mind. 8 Zeichen bestehen",
                    isPassword = true,
                    errorText = passwordError
                )

                Spacer(Modifier.size(20.dp))

                InputField(
                    passwordConfirmation,
                    {
                        registerScreenViewModel?.handleOnPasswordConfirmationChange(
                            it,
                            password,
                            setPasswordConfirmation,
                            setPasswordConfirmationError
                        )
                    },
                    "Passwort wiederholen",
                    "Passwort wiederholen",
                    isPassword = true,
                    errorText = passwordConfirmationError
                )

                Spacer(Modifier.size(20.dp))

                ErrorText(generalError)

                PrimaryButton("Registrieren") {
                    registerScreenViewModel?.onClickRegister(email, password, passwordConfirmation, displayName, setGeneralError, navigator)
                }

                Divider()

                Column(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    Arrangement.Center,
                    Alignment.CenterHorizontally,
                ) {

                    GrayText("Bereits ein Konto?")

                    TextButton({ registerScreenViewModel?.onClickLogin(navigator) }) {
                        Text("Anmelden")
                    }
                }
            }
        }
    }
}

@Preview(name = "LightMode", showBackground = true)
@Preview(name = "DarkMode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(null, EmptyDestinationsNavigator)
}