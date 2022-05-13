package com.maetzedev.shop_kotlin.uicomponents.compose.buttons

import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

/**
 * PrimaryTextButton
 * renders a TextButton with the primary color (material theme)
 * @param children composable function
 * @param onClick onClickListener
 */
@Composable
fun PrimaryTextButton(children: @Composable () -> Unit, onClick: () -> Unit) {
    TextButton(onClick) {
        children()
    }
}