package com.maetzedev.shop_kotlin.uicomponents.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

fun getKeyBoardOptions(isEmail: Boolean, isPassword: Boolean): KeyboardOptions {
    var keyboardType = KeyboardType.Text

    if (isEmail) {
        keyboardType = KeyboardType.Email
    }

    if (isPassword) {
        keyboardType = KeyboardType.Password
    }

    return KeyboardOptions(keyboardType = keyboardType)
}

// TODO: change TextField to BasicTextField to match the Adobe XD styles
/**
 * InputField
 * renders a textfield with some adjustments
 * @param value input value
 * @param onValueChange fun which updates state
 * @param label (optional)
 * @param placeHolder (optional)
 * @param hintText (optional)
 * @param isPassword shows a password field if true
 * @param isEmail shows a email field if true
 * @param errorText (optional)
 */
@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    placeHolder: String? = null,
    hintText: String? = null,
    isPassword: Boolean = false,
    isEmail: Boolean = false,
    errorText: String = "",
) {
    Column {
        if (label != null) {
            Text(text = label, fontWeight = FontWeight.Bold)
        }
        if (hintText != null) {
            Text(text = hintText, fontSize = 12.sp)
        }
        if (errorText != "") {
            Text(errorText, color = Color.Red, fontSize = 12.sp)
        }
        TextField(
            value,
            onValueChange,
            Modifier.fillMaxWidth(),
            placeholder = {
                if (placeHolder != null) {
                    Text(text = placeHolder)
                }
            },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent
            ),
            singleLine = true,
            textStyle = TextStyle(textAlign = TextAlign.Start),
            keyboardOptions = getKeyBoardOptions(isEmail, isPassword)
        )
    }
}