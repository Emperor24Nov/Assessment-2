package org.d3if3143.mobpro1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if3143.mobpro1.ui.screen.KEY_ID_MUSIC
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
        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_MUSIC) { type = NavType.LongType }
            )
        ) {navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_MUSIC)
            MusicScreen(navController,id)
        }
    }
}