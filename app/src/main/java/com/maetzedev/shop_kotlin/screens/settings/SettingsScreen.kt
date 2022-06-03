package com.maetzedev.shop_kotlin.screens.settings

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maetzedev.shop_kotlin.ui.theme.ShopkotlinTheme
import com.maetzedev.shop_kotlin.uicomponents.compose.Container
import com.maetzedev.shop_kotlin.uicomponents.compose.InputField
import com.maetzedev.shop_kotlin.uicomponents.compose.ScreenHeadline
import com.maetzedev.shop_kotlin.uicomponents.compose.buttons.PrimaryButton
import com.maetzedev.shop_kotlin.uicomponents.compose.buttons.PrimaryTextButton
import com.maetzedev.shop_kotlin.uicomponents.compose.texts.ErrorText
import com.maetzedev.shop_kotlin.uicomponents.compose.texts.TextWithIcon
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

data class EditState(
    val displayName: Boolean,
    val email: Boolean,
    val password: Boolean
)

data class ErrorState(
    val displayName: String,
    val email: String,
    val password: String,
    val general: String
)

@Destination
@Composable
fun SettingsScreen(
    settingsScreenViewModel: SettingsScreenViewModel? = SettingsScreenViewModel(),
    navigator: DestinationsNavigator
) {
    val (editMode, setEditMode) = remember {
        mutableStateOf(EditState(displayName = false, email = false, password = false))
    }
    val (error, setError) = remember {
        mutableStateOf(ErrorState(email = "", password = "", general = "", displayName = ""))
    }
    val (displayName, setDisplayName) = remember { mutableStateOf("") }
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }

    Scaffold {


        Container {
            ScreenHeadline("Einstellungen")

            Spacer(Modifier.size(20.dp))

            if (!editMode.displayName) {
                TextWithIcon(
                    "Nutzername: ${settingsScreenViewModel?.displayName}",
                    Icons.Rounded.Edit,
                    "Edit"
                ) {
                    setEditMode(
                        EditState(displayName = true, email = false, password = false)
                    )
                }
            } else {
                InputField(
                    displayName,
                    { setDisplayName(it) },
                    "Nutzername",
                    "${settingsScreenViewModel?.displayName}"
                )
            }

            Spacer(Modifier.size(20.dp))

            if (!editMode.email) {
                TextWithIcon(
                    "E-Mail: ${settingsScreenViewModel?.email}",
                    Icons.Rounded.Edit,
                    "Edit"
                ) {
                    setEditMode(
                        EditState(displayName = false, email = true, password = false)
                    )
                }
            } else {
                InputField(
                    email,
                    { settingsScreenViewModel?.handleOnEmailChange(it, setEmail, error, setError) },
                    "E-Mail",
                    "${settingsScreenViewModel?.email}",
                    isEmail = true,
                    errorText = error.email
                )
            }

            Spacer(Modifier.size(20.dp))

            if (!editMode.password) {
                TextWithIcon(
                    "Passwort",
                    Icons.Rounded.Edit,
                    "Edit"
                ) {
                    setEditMode(
                        EditState(displayName = false, email = false, password = true)
                    )
                }
            } else {
                InputField(
                    password,
                    {
                        settingsScreenViewModel?.handleOnPasswordChange(
                            it,
                            setPassword,
                            error,
                            setError
                        )
                    },
                    "Passwort",
                    "Dein neues Passwort",
                    isPassword = true,
                    errorText = error.password
                )
            }
            Spacer(Modifier.size(20.dp))

            ErrorText(error.general)

            if (editMode.displayName || editMode.email || editMode.password) {
                PrimaryButton({
                    Text("Speichern")
                }) {
                    if (editMode.displayName) {
                        settingsScreenViewModel?.updateDisplayName(
                            displayName,
                            error,
                            setError,
                            navigator
                        )
                    } else if (editMode.email) {
                        settingsScreenViewModel?.updateEmail(email, error, setError, navigator)
                    } else {
                        settingsScreenViewModel?.updatePassword(
                            password,
                            error,
                            setError,
                            navigator
                        )
                    }
                    setEditMode(EditState(displayName = false, email = false, password = false))
                }

                PrimaryTextButton({
                    Text("Abbrechen")
                }) {
                    settingsScreenViewModel?.abort(
                        setEditMode,
                        setDisplayName,
                        setEmail,
                        setPassword
                    )
                }
            } else {
                PrimaryButton({
                    Text("Logout", Modifier.padding(it.calculateTopPadding()))
                }) {
                    settingsScreenViewModel?.onClickLogout(navigator)
                }
            }

        }
    }
}

@Preview(
    name = "LightMode",
    showBackground = true,
    showSystemUi = true
)
@Preview(
    name = "DarkMode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewSettingsScreen() {
    ShopkotlinTheme {
        SettingsScreen(null, EmptyDestinationsNavigator)
    }
}