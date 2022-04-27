package com.maetzedev.shop_kotlin.uicomponents.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Container
 * renders a custom column with predefined padding for consistent styling
 * @param centerVertical centers children vertical if true
 * @param centerHorizontal centers children horizontal if true
 * @param children composable components
 */
@Composable
fun Container(
    centerVertical: Boolean = false,
    centerHorizontal: Boolean = false,
    children: @Composable () -> Unit
) {
    Column(
        Modifier.padding(start = 15.dp, top = 30.dp, end = 15.dp),
        if (centerVertical) Arrangement.Center else Arrangement.Top,
        if (centerHorizontal) Alignment.CenterHorizontally else Alignment.Start
    ) {
        children()
    }
}