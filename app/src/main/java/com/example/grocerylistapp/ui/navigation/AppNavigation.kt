package com.example.grocerylistapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.grocerylistapp.ui.screens.AddItem
import com.example.grocerylistapp.ui.screens.HomeScreen

@Composable
fun AppNavigationGraph() {

    val navController = rememberNavController()
    NavHost(

        navController = navController,
        startDestination = Screens.HomeScreen.route,
        builder = {
            composable(
                route = Screens.HomeScreen.route,
                arguments = listOf(navArgument("id") {
                    type = NavType.IntType
                })
            ) {
                HomeScreen(
                    navController = navController,
                    context = LocalContext.current
                )
            }
            composable(route = Screens.AddScreen.route) {
                AddItem(navController = navController)
            }
            
        }
    )
}

sealed class Screens(val route: String) {
    data object HomeScreen : Screens("home/{id}")
    data object AddScreen : Screens("add")
}