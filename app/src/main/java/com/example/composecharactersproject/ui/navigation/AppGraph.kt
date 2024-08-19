package com.example.composecharactersproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.composecharactersproject.ui.feature.CharacterDetailScreen.CharacterDetailScreen
import com.example.composecharactersproject.ui.feature.CharactersScreen.CharactersScreen
import com.example.composecharactersproject.ui.feature.splash.SplashScreen

@Composable
fun AppGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Routes.Splash.route) {
        composable(Routes.Splash.route) {
            SplashScreen(navController)
        }
        composable(Routes.Characters.route) {
            CharactersScreen(navController)
        }
        composable(
            Routes.CharacterDetail.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStack ->
            val id = backStack.arguments?.getString("id")
            CharacterDetailScreen(id.orEmpty())
        }
    }
}