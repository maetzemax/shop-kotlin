package com.maetzedev.shop_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.FirebaseApp
import com.maetzedev.shop_kotlin.screens.NavGraphs
import com.maetzedev.shop_kotlin.ui.theme.ShopkotlinTheme
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        setContent {
            ShopkotlinTheme {
                    DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}