package com.example.grocerylistapp.ui.detail

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.grocerylistapp.R
import com.example.grocerylistapp.ui.components.NameAndCostComponent
import com.example.grocerylistapp.ui.components.Win95StyleBarCard
import com.example.grocerylistapp.ui.theme.Gray95
import com.example.grocerylistapp.ui.theme.Teal95
import com.example.grocerylistapp.ui.theme.gradientDarkGrayToWhite
import com.example.grocerylistapp.ui.utils.ImageUtils.Companion.readBytesFromUri

@Composable
fun DetailScreen(
    navController: NavController,
    detailViewModel: DetailViewModel = hiltViewModel()
) {

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            imageUri = uri
            if (imageUri != null) {
                detailViewModel.picture =
                    readBytesFromUri(imageUri!!, navController.context)!!
            } else {
                detailViewModel.picture = byteArrayOf()
            }
        })


    Box(
        modifier = Modifier
            .background(Teal95),
    ) {
        val context: Context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .padding(16.dp)
                .background(Gray95)
                .border(
                    BorderStroke(
                        width = 0.5.dp,
                        brush = gradientDarkGrayToWhite
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Win95StyleBarCard(
                itemName = stringResource(id = R.string.add_item),
                onDeleteClicked = {
                    navController.popBackStack()
                },
                fontSize = 20.sp,
                width = 20.dp,
                height = 20.dp
            )

            Spacer(modifier = Modifier.height(16.dp))

            AsyncImage(
                model = imageUri,
                contentDescription = null,
                error = painterResource(id = R.drawable.empty_image),
                modifier = Modifier
                    .size(200.dp)
                    .clip(shape = MaterialTheme.shapes.extraSmall)
                    .background(MaterialTheme.colorScheme.outline)
                    .clickable {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                contentScale = ContentScale.Crop
            )

            NameAndCostComponent(
                addItemButton = {
                    detailViewModel.validateDataWithRules()
                    if (detailViewModel.errorStatus.productValueError) {
                        if (!detailViewModel.errorStatus.nameProductValueNotEmpty) {
                            Toast.makeText(context, "Fill text fields", Toast.LENGTH_SHORT).show()
                        } else {
                            detailViewModel.addItem()
                            navController.popBackStack()
                        }
                    } else {
                        Toast.makeText(context, "The item cost field only accepts numbers", Toast.LENGTH_SHORT).show()
                    }

                },
                onItemNameChange = { newName ->
                    detailViewModel.name = newName
                },
                onItemCostChange = { newCost ->
                    detailViewModel.productValue = newCost
                }
            )
        }
    }
}








