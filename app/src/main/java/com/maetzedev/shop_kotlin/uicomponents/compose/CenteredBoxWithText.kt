package com.maetzedev.shop_kotlin.uicomponents.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CenteredBoxWithText(text: String, paddingValues: PaddingValues) {
    Box(
        Modifier
            .padding(bottom = paddingValues.calculateBottomPadding())
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text)
    }
}