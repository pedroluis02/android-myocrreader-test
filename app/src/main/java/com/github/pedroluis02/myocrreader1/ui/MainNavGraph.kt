package com.github.pedroluis02.myocrreader1.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = MainScreenType.Home.route,
        modifier = modifier
    ) {
        composable(MainScreenType.Home.route) {
            MainView(navController)
        }

        composable(MainScreenType.Camera.route) {
            MainCameraView(navController)
        }
    }
}