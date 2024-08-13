package com.example.bottomnav.Screens.SettingsScreen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bottomnav.BuildConfig
import com.example.bottomnav.R
import com.example.bottomnav.createImageFile
import java.util.Objects

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {
    val todoList by viewModel.todoList.observeAsState()
    val imageURIs by viewModel.imageURIs.observeAsState(listOf())
    val context = LocalContext.current
    var tempUri by remember { mutableStateOf<Uri?>(null) }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { isCaptured ->
            if (isCaptured && tempUri != null) {
                viewModel.addImageUri(tempUri!!)
            }
        }

    val cameraPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                val file = context.createImageFile()
                val uri = FileProvider.getUriForFile(
                    Objects.requireNonNull(context),
                    BuildConfig.APPLICATION_ID + ".provider",
                    file
                )
                tempUri = uri
                cameraLauncher.launch(uri)
            } else {
                Toast.makeText(context, "Permission is not granted", Toast.LENGTH_LONG).show()
            }
        }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                val isPermissionGranted = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED

                if (isPermissionGranted) {
                    val file = context.createImageFile()
                    val uri = FileProvider.getUriForFile(
                        context,
                        BuildConfig.APPLICATION_ID + ".provider",
                        file
                    )
                    tempUri = uri
                    cameraLauncher.launch(uri)
                } else {
                    cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera_alt_24),
                    contentDescription = "Custom Icon",
                    tint = Color.Red
                )
            }
        }

        todoList?.let {LazyVerticalGrid(
            columns = GridCells.Fixed(2),  // Number of columns
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                items(imageURIs.size) { index ->
                    val imageUri = imageURIs[index]
                    AnimatedVisibility(visible = imageUri != Uri.EMPTY) {
                        Image(
                            bitmap = createBitmap(imageUri, context).asImageBitmap(),
                            contentDescription = "Bitmap",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        )
                    }
                }
            }
        )

        } ?: Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "No items yet",
            fontSize = 16.sp
        )
    }
}

fun createBitmap(imageUri: Uri, context: Context): Bitmap {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, imageUri))
    } else {
        MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
    }
}