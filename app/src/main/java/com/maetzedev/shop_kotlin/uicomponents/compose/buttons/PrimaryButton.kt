package com.maetzedev.shop_kotlin.uicomponents.compose.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * PrimaryButton
 * renders a button with the primary color (material theme)
 * @param text composable function
 * @param onClick onClickListener
 */
@Composable
fun PrimaryButton(text: String, onClick: () -> Unit) {
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(
            modifier = Modifier
                .background(
                    Color(
                        0xFF4160B4
                    )
                )
                .clickable { onClick() }
                .padding(10.dp),
            text = text,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White
        )
    }
}