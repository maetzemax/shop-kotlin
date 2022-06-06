package com.maetzedev.shop_kotlin.screens.product.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.maetzedev.shop_kotlin.models.product.Product
import com.maetzedev.shop_kotlin.repositories.ProductsRepo
import java.util.*

class AddProductViewModel: ViewModel() {

    private val productsRepo by lazy { ProductsRepo() }

    var nameText by mutableStateOf(TextFieldValue(""))
    var descriptionText by mutableStateOf(TextFieldValue(""))
    var priceText by mutableStateOf("0.00")
    val maxChar = 50

    private val username = FirebaseAuth.getInstance().currentUser?.displayName ?: throw Error("No UID")

    fun addNewProduct(data: ByteArray) {
       productsRepo.addNewProduct(
           Product(
               docId = "$username-${UUID.randomUUID().toString()}",
               id = 0, created = Timestamp.now(),
               name = nameText.text,
               description = descriptionText.text,
               price = priceText.toDouble(),
               seller = "",
               isLiked = false,
               isInCart = false,
               category = "",
               imageUrl = ""
           )
           , imageByteArray = data
       )
    }


}