package com.maetzedev.shop_kotlin.uicomponents.compose.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * PrimaryButton
 * renders a button with the primary color (material theme)
 * @param children composable function
 * @param onClick onClickListener
 */
@Composable
fun PrimaryButton(children: @Composable () -> Unit, onClick: () -> Unit) {
    Button(
        onClick,
        Modifier.fillMaxWidth(),
        shape = CircleShape
    ) {
        children()
    }
}