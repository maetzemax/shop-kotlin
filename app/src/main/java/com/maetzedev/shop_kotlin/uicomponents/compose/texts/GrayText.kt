package com.maetzedev.shop_kotlin.uicomponents.compose.texts

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * GrayText
 * renders a Text View with gray text
 * @param text String
 */
@Composable
fun GrayText(text: String) {
    Text(
        text,
        color = Color.Gray
    )
}