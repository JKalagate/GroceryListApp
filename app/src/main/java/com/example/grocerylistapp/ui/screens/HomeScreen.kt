package com.example.grocerylistapp.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Card
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.grocerylistapp.R
import com.example.grocerylistapp.ui.navigation.Screens
import com.example.grocerylistapp.ui.theme.DarkGray95
import com.example.grocerylistapp.ui.theme.windows95Family
import com.example.grocerylistapp.ui.viewModels.HomeViewModel
import com.example.grocerylistapp.ui.theme.Gray95
import com.example.grocerylistapp.ui.theme.MediumGray95
import com.example.grocerylistapp.ui.theme.Teal95
import com.example.grocerylistapp.ui.theme.gradientDarkBlueToLightBlue
import com.example.grocerylistapp.ui.theme.gradientDarkGrayToWhite
import com.example.grocerylistapp.ui.theme.gradientWhiteToDarkGray


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    context: Context
) {
    viewModel.updateTotalAmount()
    val totalCost by viewModel.totalAmount.collectAsState(initial = 0.0)
    val groceryList = viewModel.listItem.collectAsState(initial = emptyList())

    Scaffold(
        containerColor = Teal95,
        bottomBar = {
            BottomAppBar(
                containerColor = Gray95,
                modifier = Modifier
                    .border(
                        BorderStroke(
                            width = 1.dp,
                            brush = gradientDarkGrayToWhite
                        )
                    ),
                content = {
                    ButtonBarWin95(totalCost.toString(), navController)
                })
        },
    ) { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier.padding(innerPadding),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            content = {
                items(
                    groceryList.value
                ) { item ->
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
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(1.dp)
                        ) {
                            Win95StyleBarCard(
                                itemName = item.name,
                                viewModel = viewModel,
                                deletedItem = item
                                )
                            ImageItemCostButtonsLayer(
                                unitCost = item.productValue,
                                itemAmount = item.amount,
                                viewModel = viewModel,
                                id = item.id,
                                context = context,
                                image = item.picture
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        )
    }
}











