package com.example.grocerylistapp.ui.screens

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.grocerylistapp.ui.theme.Gray95
import com.example.grocerylistapp.ui.theme.Teal95
import com.example.grocerylistapp.ui.viewModels.AddViewModel

@Composable
fun AddItem(
    navController: NavController,
    viewModel: AddViewModel = hiltViewModel()
) {

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            imageUri = uri

            if (imageUri != null) {
                viewModel.picture =
                    AddViewModel.readBytesFromUri(imageUri!!, navController.context)!!
            } else {
                viewModel.picture = byteArrayOf()
            }

        })

    val context: Context = LocalContext.current

    Box(
        modifier = Modifier
            .background(Teal95),
    ) {
        AddItemLayout(
            imageUri = imageUri,
            singlePhotoPickerLauncher = singlePhotoPickerLauncher,
            viewModel = viewModel,
            navController = navController,
            context = context
        )
    }
}







