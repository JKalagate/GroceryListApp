package com.example.grocerylistapp.ui.screens

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.grocerylistapp.R
import com.example.grocerylistapp.data.entity.GroceryItem
import com.example.grocerylistapp.ui.navigation.Screens
import com.example.grocerylistapp.ui.theme.Gray95
import com.example.grocerylistapp.ui.theme.MediumGray95
import com.example.grocerylistapp.ui.theme.gradientDarkBlueToLightBlue
import com.example.grocerylistapp.ui.theme.gradientDarkGrayToWhite
import com.example.grocerylistapp.ui.theme.gradientWhiteToDarkGray
import com.example.grocerylistapp.ui.theme.windows95Family
import com.example.grocerylistapp.ui.viewModels.AddViewModel
import com.example.grocerylistapp.ui.viewModels.HomeViewModel

@Composable
fun AddItemLayout(
    imageUri: Uri?,
    singlePhotoPickerLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
    viewModel: AddViewModel,
    navController: NavController,
    context: Context,
) {
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

        Win95StyleBarCardAddItem(navController)

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


        ItemNameAndCost(viewModel, navController, context)
    }
}


//@Preview(showBackground = true)
//@Composable
//fun MainLayoutPrev() {
//    MainLayout()
//}

@Composable
fun ItemNameAndCost(
    viewModel: AddViewModel,
    navController: NavController,
    context: Context
) {

    Column(
        modifier = Modifier
            .background(Gray95)
            .fillMaxWidth()
            .height(300.dp)
            .padding(16.dp)
            .border(
                BorderStroke(
                    width = 0.5.dp,
                    brush = gradientDarkGrayToWhite
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(8.dp))

        Row() {
            Text(
                text = "Item Name:",
                modifier = Modifier.width(150.dp),
                fontFamily = windows95Family,
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
                color = Color.Black


            )
            BasicTextField(
                value = viewModel.name,
                onValueChange = { viewModel.name = it },
                textStyle = TextStyle(
                    fontFamily = windows95Family,
                    fontSize = 24.sp,
                ),

                singleLine = true,
                enabled = true,
                modifier = Modifier
                    .background(color = Color.White)
                    .sizeIn(maxWidth = 135.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {

            Text(
                text = "Item Cost:",
                modifier = Modifier.width(150.dp),
                fontFamily = windows95Family,
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
                color = Color.Black
            )

            BasicTextField(
                value = viewModel.productValue,
                onValueChange = { viewModel.productValue = it },
                textStyle = TextStyle(
                    fontFamily = windows95Family,
                    fontSize = 24.sp,
                ),
                singleLine = true,
                enabled = true,
                modifier = Modifier
                    .background(color = Color.White)
                    .sizeIn(maxWidth = 135.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        TextButton(
            modifier = Modifier
                .background(Gray95)
                .width(300.dp)
                .border(
                    BorderStroke(
                        width = 0.5.dp,
                        brush = gradientWhiteToDarkGray
                    )
                ),
            onClick = {
                val matchRegex = "^[0-9.]+\$".toRegex()

                if (viewModel.productValue.matches(matchRegex)) {

                    if (viewModel.name.isEmpty() || viewModel.productValue.isEmpty()) {
                        Toast.makeText(context, "Fill text fields", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.addItem()
                        navController.popBackStack()
                    }
                } else {
                    Toast.makeText(context, "The item cost field only accepts numbers", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text(
                text = "ADD NEW ITEM",
                fontFamily = windows95Family,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 18.sp,
                maxLines = 1
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ItemNameAndCostPrev() {
//    ItemNameAndCost()
//}

@Composable
fun Win95StyleBarCardAddItem(
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(gradientDarkBlueToLightBlue)
            .padding(3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Add Item",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = windows95Family,
            fontWeight = FontWeight.Light,
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .background(Gray95)
                .border(
                    BorderStroke(
                        width = 0.5.dp,
                        brush = gradientWhiteToDarkGray
                    )
                )
                .size(20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "backToHomeLayout",
                tint = Color.Black,
                modifier = Modifier
                    .size(14.dp)
            )

        }
    }
}


