package com.maetzedev.shop_kotlin.ui.atoms


import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ScreenHeadline(text: String) {
    Text(text, fontSize = 34.sp, fontWeight = FontWeight.Bold)
}
