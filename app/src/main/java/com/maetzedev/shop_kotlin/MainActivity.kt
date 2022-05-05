package com.maetzedev.shop_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.maetzedev.shop_kotlin.screens.auth.register.RegisterScreen
import com.maetzedev.shop_kotlin.screens.home.HomeScreen
import com.maetzedev.shop_kotlin.ui.theme.ShopkotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        setContent {
            ShopkotlinTheme {
                Surface {
                    // TODO: Implement Start Screen
                    HomeScreen()
                }
            }
        }
    }
}