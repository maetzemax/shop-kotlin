package com.maetzedev.shop_kotlin.ui.atoms

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

// TODO: change TextField to BasicTextField to match the Adobe XD styles
@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    placeHolder: String? = null,
    hintText: String? = null,
    isPassword: Boolean = false,
    isEmail: Boolean = false,
) {
    Column {
        if (label != null) {
            Text(text = label, fontWeight = FontWeight.Bold)
        }
        
        if (hintText != null) {
            Text(text = hintText, fontSize = 12.sp)
        }

        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                if (placeHolder != null) {
                    Text(text = placeHolder)
                }
            },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent
            ),
            singleLine = true,
            textStyle = TextStyle(textAlign = TextAlign.Start),
            keyboardOptions = KeyboardOptions(keyboardType = if (isEmail) KeyboardType.Email else KeyboardType.Text)
        )
    }
}