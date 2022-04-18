package com.maetzedev.shop_kotlin

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.maetzedev.shop_kotlin.auth.PasswordTooShort
import com.maetzedev.shop_kotlin.auth.PasswordsDontMatch
import com.maetzedev.shop_kotlin.auth.RegisterFailed
import com.maetzedev.shop_kotlin.auth.UserAuth
import com.maetzedev.shop_kotlin.ui.screen.RegisterScreen
import com.maetzedev.shop_kotlin.ui.theme.ShopkotlinTheme

class MainActivity : ComponentActivity() {
    private var userAuth = UserAuth()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShopkotlinTheme {
                Surface {
                    // TODO: Implement Start Screen
                    RegisterScreen(
                        onClickRegister = { email, password ->
                            try {
                                userAuth.register(email, password)
                            } catch (e: RegisterFailed) {
                                Log.e("E/Registration", e.message.toString())
                            } catch (e: PasswordTooShort) {
                                Log.e("E/Registration", e.message.toString())
                            } catch (e: PasswordsDontMatch) {
                                Log.e("E/Registration", e.message.toString())
                            }
                            // send to home screen
                        },
                        onClickLogin = {
                            Log.d("Registration", "Login Button pressed")
                        }
                    )
                }
            }
        }
    }
}