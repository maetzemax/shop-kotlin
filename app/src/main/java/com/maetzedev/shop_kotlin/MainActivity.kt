package com.maetzedev.shop_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.maetzedev.shop_kotlin.screens.auth.register.RegisterScreen
import com.maetzedev.shop_kotlin.ui.theme.ShopkotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShopkotlinTheme {
                Surface {
                    // TODO: Implement Start Screen
                    RegisterScreen()
                }
            }
        }
    }
}