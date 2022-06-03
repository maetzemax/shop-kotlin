package com.maetzedev.shop_kotlin.uicomponents.compose.texts

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TextTable(textLeft: String, textRight: String) {
    Row(Modifier.fillMaxWidth()) {
        Text(
            textLeft,
            Modifier.weight(1F)
        )

        Text(
            textRight,
            Modifier.weight(1F),
            textAlign = TextAlign.End
        )
    }
}