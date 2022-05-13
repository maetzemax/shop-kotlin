package com.maetzedev.shop_kotlin.uicomponents.compose


import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Screenheadline
 * renders a custom text element with predefined styles
 * @param text
 */
@Composable
fun ScreenHeadline(text: String) {
    Text(text, fontSize = 34.sp, fontWeight = FontWeight.Bold)
}
