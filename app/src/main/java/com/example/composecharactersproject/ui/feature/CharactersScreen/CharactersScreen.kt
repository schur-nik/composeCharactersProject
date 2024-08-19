package com.example.composecharactersproject.ui.feature.CharactersScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.composecharactersproject.ui.navigation.Routes
import com.example.composecharactersproject.model.Character

@Composable
fun CharactersScreen(
    navHostController: NavHostController,
    viewModel: CharactersViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    state.run {
                        if (state.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                        if (listCharacter.isNotEmpty()) {
                            BlockCharacter(
                                title = "Characters",
                                list = listCharacter,
                                modifier = Modifier.padding(15.dp),
                                onClick = { character ->
                                    navHostController.navigate(Routes.CharacterDetail.createRoute(character.name))
                                }
                            )
                        } else {
                            Text(text = "NO_DATA_FOUND")
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun BlockCharacter(
    title: String,
    list: List<Character>,
    modifier: Modifier = Modifier,
    onClick: (character: Character) -> Unit = {}
) {
    if (list.isNotEmpty()) {
        Column(modifier = modifier) {
            Text(text = title, modifier = Modifier.align(Alignment.CenterHorizontally))
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(list) { character ->
                    Box(modifier = Modifier.clickable { onClick(character) }) {
                        Column(modifier = Modifier
                            .border(1.dp, Color.Red)
                            .fillMaxSize()) {
                            Text(
                                text = character.name,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            AsyncImage(
                                model = character.ability?.sprites?.image,
                                contentDescription = character.name,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }
            }
        }
    }
}

