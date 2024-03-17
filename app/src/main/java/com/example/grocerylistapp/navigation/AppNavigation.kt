package com.example.grocerylistapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.grocerylistapp.ui.detail.DetailScreen
import com.example.grocerylistapp.ui.home.HomeScreen

@Composable
fun AppNavigationGraph() {

    val navController = rememberNavController()
    NavHost(

        navController = navController,
        startDestination = Screens.HomeScreen.route,
        builder = {
            composable(route = Screens.HomeScreen.route) {
                HomeScreen(
                    navController = navController,
                    context = LocalContext.current
                )
            }
            composable(route = Screens.AddScreen.route) {
                DetailScreen(navController = navController)
            }
            
        }
    )
}

sealed class Screens(val route: String) {
    data object HomeScreen : Screens("home")
    data object AddScreen : Screens("add")
}