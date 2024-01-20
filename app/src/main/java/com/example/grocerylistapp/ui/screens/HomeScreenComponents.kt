package com.example.grocerylistapp.ui.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.grocerylistapp.R
import com.example.grocerylistapp.data.entity.GroceryItem
import com.example.grocerylistapp.ui.navigation.Screens
import com.example.grocerylistapp.ui.theme.Gray95
import com.example.grocerylistapp.ui.theme.MediumGray95
import com.example.grocerylistapp.ui.theme.gradientDarkBlueToLightBlue
import com.example.grocerylistapp.ui.theme.gradientDarkGrayToWhite
import com.example.grocerylistapp.ui.theme.gradientWhiteToDarkGray
import com.example.grocerylistapp.ui.theme.windows95Family
import com.example.grocerylistapp.ui.viewModels.HomeViewModel


@Composable
fun Win95StyleBarCard(
    itemName: String,
    viewModel: HomeViewModel,
    deletedItem: GroceryItem
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(gradientDarkBlueToLightBlue)
            .padding(3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = itemName,
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = windows95Family,
            fontWeight = FontWeight.Light,
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = {
                viewModel.deleteNote(deletedItem)
            },
            modifier = Modifier
                .background(Gray95)
                .border(
                    BorderStroke(
                        width = 0.5.dp,
                        brush = gradientWhiteToDarkGray
                    )
                )
                .size(14.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier
                    .size(14.dp)
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun Win95StyleBarCardPrev() {
//    Win95StyleBarCard("Milk")
//}

@Composable
fun ImageItemCostButtonsLayer(
    unitCost: String,
    itemAmount: String,
    viewModel: HomeViewModel,
    id: Int,
    context: Context,
    image: ByteArray
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 3.dp)
            .height(200.dp)
            .width(160.dp)
            .border(
                BorderStroke(
                    width = 0.5.dp,
                    brush = gradientDarkGrayToWhite
                )
            ),
    ) {

        var bitmap: Bitmap = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.empty_image
        )

        if (image.isNotEmpty()) {
            bitmap = BitmapFactory.decodeByteArray(
                image,
                0,
                image.size
            )
        }
        val imageBitmap = bitmap.asImageBitmap()


        Image(
            bitmap = imageBitmap,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
        )

        ItemCostLayer(unitCost, itemAmount, viewModel, id)

    }
}


//@Preview(showBackground = true)
//@Composable
//fun ImageLayerPrev() {
//    ImageItemCostButtonsLayer("25", "2")
//}

@Composable
fun ItemCostLayer(
    unitCost: String,
    itemAmount: String,
    viewModel: HomeViewModel,
    id: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(3.dp)
            .width(140.dp)
            .height(90.dp)
            .border(
                BorderStroke(
                    width = 0.5.dp,
                    brush = gradientDarkGrayToWhite
                )
            ),
    ) {

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Unit cost: $unitCost",
            fontSize = 12.sp,
            fontFamily = windows95Family,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))

        BasicTextField(
            value = itemAmount,
            onValueChange = { itemAmount },
            textStyle = TextStyle(
                fontFamily = windows95Family,
                fontSize = 14.sp,
            ),
            enabled = true,
            modifier = Modifier
                .background(color = Color.White)
                .height(20.dp)
                .width(90.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        ButtonsLayer(viewModel, id)

    }
}

//@Preview(showBackground = true)
//@Composable
//fun ItemCostLayerPrev() {
//    ItemCostLayer("25", "2")
//}


@Composable
fun ButtonsLayer(
    viewModel: HomeViewModel,
    id: Int
) {

    Row(
        modifier = Modifier
            .width(90.dp)
            .background(Gray95)
    ) {
        IconButton(
            onClick = {
                viewModel.increaseItemAmount(id)
            },
            modifier = Modifier
                .background(Gray95)
                .border(
                    BorderStroke(
                        width = 0.5.dp,
                        brush = gradientWhiteToDarkGray
                    )
                )
                .height(20.dp)
                .width(44.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "increase",
                tint = Color.Black
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = {
                viewModel.decreaseItemAmount(id)
            },
            modifier = Modifier
                .background(Gray95)
                .border(
                    BorderStroke(
                        width = 0.5.dp,
                        brush = gradientWhiteToDarkGray
                    )
                )
                .height(20.dp)
                .width(44.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "decrease",
                tint = Color.Black
            )
        }


    }
}

//@Preview(showBackground = true)
//@Composable
//fun ButtonsLayerPrev() {
//    ButtonsLayer()
//}


@Composable
fun ButtonBarWin95(
    totalCost: String,
    navController: NavController
    ) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Total cost: $totalCost",
            fontSize = 18.sp,
            fontFamily = windows95Family,
            color = Color.Black

        )
        IconButton(
            onClick = {
                navController.navigate(Screens.AddScreen.route)
            },
            modifier = Modifier
                .background(MediumGray95)
                .border(
                    BorderStroke(
                        width = 0.5.dp,
                        brush = gradientWhiteToDarkGray
                    )
                )
                .size(60.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "addItem",
                tint = Color.Black
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ButtonBarWin95Prev() {
//    ButtonBarWin95("200")
//}