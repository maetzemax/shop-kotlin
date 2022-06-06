package com.maetzedev.shop_kotlin.screens.product.add

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maetzedev.shop_kotlin.screens.destinations.HomeScreenDestination
import com.maetzedev.shop_kotlin.ui.theme.ShopkotlinTheme
import com.maetzedev.shop_kotlin.uicomponents.compose.BottomBar
import com.maetzedev.shop_kotlin.uicomponents.compose.TopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import java.io.ByteArrayOutputStream

@Destination
@Composable
fun AddProductView(viewModel: AddProductViewModel = AddProductViewModel(), navigator: DestinationsNavigator) {

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }

    Scaffold(
        topBar = { TopBar(title = "Anbieten", navigator = navigator) },
        bottomBar = { BottomBar(navigator = navigator) }
    ) {

    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(bottom = it.calculateBottomPadding(), top = 20.dp)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.Start
        ) {
            item {

                TextField(
                    value = viewModel.nameText,
                    singleLine = true,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                    ,
                    onValueChange = {
                        if (it.text.length <= viewModel.maxChar) {
                            viewModel.nameText = it
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                    ),
                    label = { Text("Produktname", fontWeight = FontWeight.SemiBold) }
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = viewModel.descriptionText,
                    modifier = Modifier
                        .fillMaxWidth(),
                    onValueChange = { viewModel.descriptionText = it },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                    ),
                    label = { Text("Produktbeschreibung", fontWeight = FontWeight.SemiBold) }
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = viewModel.priceText,
                    modifier = Modifier
                        .fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    onValueChange = { viewModel.priceText = it },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                    ),
                    label = { Text("Preis in €", fontWeight = FontWeight.SemiBold) }
                )

                imageUri.let {
                    if (Build.VERSION.SDK_INT < 28) {
                        bitmap.value = MediaStore.Images
                            .Media.getBitmap(context.contentResolver, it)
                    } else {
                        val source =
                            it?.let { it1 ->
                                ImageDecoder.createSource(
                                    context.contentResolver,
                                    it1
                                )
                            }
                        bitmap.value = source?.let { it1 -> ImageDecoder.decodeBitmap(it1) }
                    }

                    Button(
                        onClick = { launcher.launch("image/*") },
                        modifier = Modifier.padding(vertical = 5.dp),
                    ) {
                        Text("Öffne Galerie")
                    }

                    bitmap.value?.let { btm ->

                        val baos = ByteArrayOutputStream()
                        btm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                        val data: ByteArray = baos.toByteArray()

                        Image(
                            bitmap = btm.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .aspectRatio(1.9f)
                                .padding(20.dp)
                        )

                        Button(
                            onClick = {
                                if (data.isNotEmpty() && viewModel.nameText.text.isNotEmpty() && viewModel.descriptionText.text.isNotEmpty() && viewModel.priceText.toDouble() != 0.00) {
                                    viewModel.addNewProduct(data)
                                    Toast.makeText(context, "Artikel angeboten!", Toast.LENGTH_LONG)
                                        .show()
                                    navigator.navigate(HomeScreenDestination)
                                } else {
                                    Toast.makeText(context, "Eingabe fehlgeschlagen!", Toast.LENGTH_LONG)
                                        .show()
                                }

                            },
                            modifier = Modifier.padding(vertical = 5.dp)
                        ) {
                            Text("Absenden")
                        }
                    }

                }
            }
        }
    }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
fun AddProductView_Preview() {
    ShopkotlinTheme {
        AddProductView(navigator = EmptyDestinationsNavigator)

    }
}