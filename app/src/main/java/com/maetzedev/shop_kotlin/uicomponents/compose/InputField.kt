package com.maetzedev.shop_kotlin.uicomponents.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
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
    val interactionSource = remember { MutableInteractionSource() }

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
        BasicTextField(
            value,
            onValueChange,
            Modifier
                .fillMaxWidth()
                .indicatorLine(
                    enabled = true,
                    isError = errorText.isNotEmpty(),
                    interactionSource = interactionSource,
                    colors = TextFieldDefaults.textFieldColors()
                ),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            singleLine = true,
            keyboardOptions = getKeyBoardOptions(isEmail, isPassword),
        ) { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = value,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
                interactionSource = interactionSource,
                placeholder = {
                    if (placeHolder != null) Text(placeHolder)
                },
                contentPadding = PaddingValues(start = 0.dp, end = 0.dp, top = 10.dp, bottom = 10.dp),
            )
        }
    }
}