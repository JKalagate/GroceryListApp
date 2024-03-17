package com.example.grocerylistapp.ui.home

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.grocerylistapp.navigation.Screens
import com.example.grocerylistapp.ui.components.ButtonBarComponent
import com.example.grocerylistapp.ui.components.GroceryCardComponent
import com.example.grocerylistapp.ui.theme.Teal95


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    context: Context
) {

    val totalCost by homeViewModel.totalAmount.collectAsState(initial = 0.0)
    val groceryList = homeViewModel.listItem.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = Unit) {
        homeViewModel.updateTotalAmount()
    }

    Scaffold(
        containerColor = Teal95,
        bottomBar = {
            ButtonBarComponent(
                totalCost = totalCost.toString(),
                onAddClicked = {
                    navController.navigate(Screens.AddScreen.route)
                }
            )
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
                    GroceryCardComponent(
                        itemName = item.name,
                        itemAmount = item.amount ,
                        unitCost = item.productValue,
                        imageBitmap = homeViewModel.decodeImage(context, item.picture),
                        onDeleteClicked = {
                            homeViewModel.deleteNote(item)
                        },
                        onIncreaseClicked = {
                            homeViewModel.increaseItemAmount(item)
                        },
                        onDecreaseClicked = {
                            homeViewModel.decreaseItemAmount(item)
                        }
                    )
                }
            }
        )
    }
}











