package com.example.composecharactersproject.ui.feature.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composecharactersproject.ui.navigation.Routes

@Composable
fun SplashScreen(
    navHostController: NavHostController,
    viewModel: SplashViewModel = hiltViewModel()
) {

    Scaffold(
        content = { padding ->
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        text = "Welcom to Disneys!",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
//                        modifier = Modifier
//                            .padding(all = 30.dp)
                    )
                    SplashImage()
                    Spacer(modifier = Modifier.height(200.dp))
                    Button(
                        onClick = {
                            navHostController.navigate(Routes.Characters.route)
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Button")
                    }
                }
            }
        }
    )

}

@Composable
fun SplashImage() {
}
