package org.d3if3143.mobpro1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if3143.mobpro1.ui.screen.Mainscreen
import org.d3if3143.mobpro1.ui.screen.MusicScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController,
        startDestination = Screen.Home.route
        ) {
        composable(route = Screen.Home.route
        ) {
            Mainscreen(navController)
        }
        composable(route = Screen.FormMusic.route) {
            MusicScreen(navController)
        }
    }
}