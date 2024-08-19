package com.example.composecharactersproject.ui.navigation

sealed class Routes(val route: String) {

    data object Splash : Routes("Splash")
    data object Characters : Routes("Characters")
    data object CharacterDetail : Routes("Characters/{id}") {
        fun createRoute(id: String): String {
            return "Characters/$id"
        }
    }

}