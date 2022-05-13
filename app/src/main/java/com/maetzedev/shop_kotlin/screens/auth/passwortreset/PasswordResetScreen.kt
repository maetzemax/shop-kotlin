package com.maetzedev.shop_kotlin.screens.auth.passwortreset

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maetzedev.shop_kotlin.uicomponents.compose.Container
import com.maetzedev.shop_kotlin.uicomponents.compose.InputField
import com.maetzedev.shop_kotlin.uicomponents.compose.ScreenHeadline
import com.maetzedev.shop_kotlin.uicomponents.compose.buttons.PrimaryButton
import com.maetzedev.shop_kotlin.uicomponents.compose.texts.ErrorText
import com.ramcosta.composedestinations.annotation.Destination

/**
 * PasswordResetScreen
 * params are optional because otherwise previews don't work
 * @param passwordResetScreenViewModel PasswortResetScreenViewModel
 */
@Destination()
@Composable
fun PasswordResetScreen(passwordResetScreenViewModel: PasswordResetScreenViewModel? = PasswordResetScreenViewModel()) {
    val (email, setEmail) = remember { mutableStateOf("") }
    val (emailError, setEmailError) = remember { mutableStateOf("") }
    val (generalError) = remember { mutableStateOf("") }

    Container {
        ScreenHeadline("Passwort Zurücksetzen")

        Spacer(Modifier.size(100.dp))

        InputField(
            email,
            { passwordResetScreenViewModel?.handleOnEmailChange(it, setEmail, setEmailError)},
            "E-Mail Adresse",
            "example@website.com",
            isEmail = true,
            errorText = emailError
        )

        ErrorText(generalError)

        PrimaryButton({
            Text("Absenden")
        }) { passwordResetScreenViewModel?.onClickPasswordReset(email) }
    }
}

@Preview(name = "Lightmode", showBackground = true)
@Preview(name = "Darkmode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewPasswortResetScreen() {
    PasswordResetScreen(null)
}