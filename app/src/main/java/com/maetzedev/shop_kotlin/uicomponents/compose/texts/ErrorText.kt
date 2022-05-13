package com.maetzedev.shop_kotlin.uicomponents.compose.texts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

/**
 * ErrorText
 * renders a Text View with a red error text
 * @param text String
 */
@Composable
fun ErrorText(text: String) {
    Text(
        text,
        Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = Color.Red
    )
}