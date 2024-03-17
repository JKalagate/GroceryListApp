package com.example.grocerylistapp.ui.components

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
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grocerylistapp.R
import com.example.grocerylistapp.ui.theme.Gray95
import com.example.grocerylistapp.ui.theme.gradientDarkBlueToLightBlue
import com.example.grocerylistapp.ui.theme.gradientDarkGrayToWhite
import com.example.grocerylistapp.ui.theme.gradientWhiteToDarkGray
import com.example.grocerylistapp.ui.theme.windows95Family


@Composable
fun TextComponent(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.White,
    fontSize: TextUnit,
    fontWeight: FontWeight
) {
    Text(
        modifier = modifier,
        text = text,
        color = textColor,
        fontSize = fontSize,
        fontFamily = windows95Family,
        fontWeight = fontWeight,
    )
}

@Composable
fun IconComponent(
    onClicked: () -> Unit,
    imageVector: ImageVector,
    height: Dp = 14.dp,
    width: Dp = 14.dp,
    iconSize: Dp = 14.dp
) {
    IconButton(
        onClick = { onClicked.invoke() },
        modifier = Modifier
            .background(Gray95)
            .border(
                BorderStroke(
                    width = 0.5.dp,
                    brush = gradientWhiteToDarkGray
                )
            )
            .height(height)
            .width(width)
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            tint = Color.Black,
            modifier = Modifier
                .size(iconSize)
        )
    }
}

@Composable
fun BasicTextFieldComponent(
    value: String
) {
    BasicTextField(
        value = value,
        onValueChange = { value },
        textStyle = TextStyle(
            fontFamily = windows95Family,
            fontSize = 14.sp,
        ),
        enabled = false,
        modifier = Modifier
            .background(color = Color.White)
            .height(20.dp)
            .width(90.dp)
    )
}


@Composable
fun Win95StyleBarCard(
    itemName: String,
    onDeleteClicked: () -> Unit,
    fontSize: TextUnit = 14.sp,
    height: Dp = 14.dp,
    width: Dp = 14.dp
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(gradientDarkBlueToLightBlue)
            .padding(3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextComponent(
            text = itemName,
            fontSize = fontSize,
            fontWeight = FontWeight.Light
        )

        Spacer(modifier = Modifier.weight(1f))

        IconComponent(
            onClicked = { onDeleteClicked.invoke() },
            imageVector = Icons.Default.Close,
            height,
            width
        )
    }
}

@Composable
fun GroceryCardComponent(
    itemName: String,
    unitCost: String,
    itemAmount: String,
    imageBitmap: ImageBitmap,
    onDeleteClicked: () -> Unit,
    onIncreaseClicked: () -> Unit,
    onDecreaseClicked: () -> Unit,
) {

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = Gray95,
        ),
        modifier = Modifier
            .height(240.dp)
            .width(180.dp)
            .border(
                BorderStroke(
                    width = 0.5.dp,
                    brush = gradientWhiteToDarkGray
                )
            )
    ) {
        Win95StyleBarCard(
            itemName = itemName,
            onDeleteClicked = {
                onDeleteClicked.invoke()
            }
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .border(
                    BorderStroke(
                        width = 0.5.dp,
                        brush = gradientDarkGrayToWhite
                    )
                ),
        ) {
            Image(
                bitmap = imageBitmap,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
            )
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

                TextComponent(
                    text = "Unit cost: $unitCost",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    textColor = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                BasicTextFieldComponent(value = itemAmount)

                Row(
                    modifier = Modifier
                        .width(90.dp)
                        .background(Gray95)
                ) {

                    IconComponent(
                        onClicked = { onIncreaseClicked.invoke() },
                        imageVector = Icons.Default.KeyboardArrowUp,
                        height = 20.dp,
                        width = 44.dp
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    IconComponent(
                        onClicked = { onDecreaseClicked.invoke() },
                        imageVector = Icons.Default.KeyboardArrowDown,
                        height = 20.dp,
                        width = 44.dp
                    )
                }
            }
        }
    }
}


@Composable
fun ButtonBarComponent(
    totalCost: String,
    onAddClicked: () -> Unit
) {
    BottomAppBar(
        containerColor = Gray95,
        modifier = Modifier
            .border(
                BorderStroke(
                    width = 1.dp,
                    brush = gradientDarkGrayToWhite
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextComponent(
                text = "Total cost: $totalCost",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                textColor = Color.Black
            )

            IconComponent(
                onClicked = { onAddClicked.invoke() },
                imageVector = Icons.Default.Add,
                height = 60.dp,
                width = 60.dp,
                iconSize = 24.dp
            )
        }
    }
}


@Composable
fun BasicTextFieldDetailComponent(
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    onTextFieldChange: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    BasicTextField(
        value = text,
        onValueChange = {
            text = it
            onTextFieldChange(it)
        },
        textStyle = TextStyle(
            fontFamily = windows95Family,
            fontSize = 24.sp,
        ),
        singleLine = true,
        enabled = true,
        modifier = Modifier
            .background(color = Color.White)
            .sizeIn(maxWidth = 135.dp),
        keyboardOptions = keyboardOptions
    )
}


@Composable
fun NameAndCostComponent(
    onItemNameChange: (String) -> Unit,
    onItemCostChange: (String) -> Unit,
    addItemButton: () -> Unit
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

        Row {
            TextComponent(
                text = stringResource(id = R.string.item_name),
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.width(150.dp),
                textColor = Color.Black
            )

            BasicTextFieldDetailComponent(
                onTextFieldChange = { onItemNameChange(it) }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            TextComponent(
                text = stringResource(id = R.string.item_cost),
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.width(150.dp),
                textColor = Color.Black
            )

            BasicTextFieldDetailComponent(
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                onTextFieldChange = { onItemCostChange(it) }
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
                addItemButton.invoke()
            }
        ) {
            TextComponent(
                text = stringResource(id = R.string.add_new_item),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textColor = Color.Black
            )
        }

    }
}







