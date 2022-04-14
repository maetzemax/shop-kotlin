package com.maetzedev.shop_kotlin

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.maetzedev.shop_kotlin.ui.screen.RegisterScreen
import com.maetzedev.shop_kotlin.ui.theme.ShopkotlinTheme

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        Log.e("TEST", auth.currentUser.toString())

        setContent {
            ShopkotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    // TODO: Implement Start Screen
                    RegisterScreen(
                        onClickRegister = {
                            Log.d("D/Registration", "Register Button pressed")
                        },
                        onClickLogin = {
                            Log.d("D/Registration", "Login Button pressed")
                        }
                    )
                }
            }
        }
    }
}