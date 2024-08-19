package com.example.composecharactersproject.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.composecharactersproject.ui.navigation.AppGraph
import com.example.composecharactersproject.ui.theme.ComposeCharactersProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCharactersProjectTheme {
                AppGraph(navController = rememberNavController())
            }
        }
    }
}
