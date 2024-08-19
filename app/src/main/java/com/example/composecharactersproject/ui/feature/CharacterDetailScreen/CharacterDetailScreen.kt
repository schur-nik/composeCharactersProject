package com.example.composecharactersproject.ui.feature.CharacterDetailScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.composecharactersproject.model.Ability
import com.example.composecharactersproject.ui.feature.CharacterDetailScreen.domain.CharacterDetailAction

@Composable
fun CharacterDetailScreen(
    id: String,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    Log.e("testDetail", id)
    val state by viewModel.state.collectAsState()

    viewModel.processedAction(CharacterDetailAction.Init(id))

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                state.run {
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .fillMaxSize()
                        )
                    }
                    if (ability != null) {
                        BlockDetail(
                            title = "Info " + ability.name,
                            ability = ability,
                            modifier = Modifier.padding(15.dp)
                        )
                    } else {
                        Text(text = "NO_DATA_FOUND")
                    }
                }
            }
        }
    )
}

@Composable
fun BlockDetail(
    title: String,
    ability: Ability,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                modifier = modifier,
                fontSize = 24.sp,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        }
        Row {
            Box(
                modifier = Modifier
                    .weight(5f, true)
                    .background(color = Color.White)
            ) {
                Text(text = "image")
                AsyncImage(
                    model = ability.sprites?.image,
                    contentDescription = "image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.5f)
                )
            }
            Box(
                modifier = Modifier
                    .weight(5f, true)
                    .background(color = Color.White)
            ) {
                Text(text = "backImage")
                AsyncImage(
                    model = ability.sprites?.backImage,
                    contentDescription = "backImage",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.5f)
                )
            }
        }
    }
}

