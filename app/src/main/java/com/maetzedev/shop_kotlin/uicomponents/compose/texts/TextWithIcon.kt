package com.maetzedev.shop_kotlin.uicomponents.compose.texts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun TextWithIcon(text: String, icon: ImageVector, contentDescription: String, onClick: () -> Unit) {
    Row(Modifier.clickable {
        onClick()
    }) {
        Text(
            text,
            Modifier.padding(end = 10.dp)
        )
        Icon(
            icon,
            contentDescription
        )
    }
}