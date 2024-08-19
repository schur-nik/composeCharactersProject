package com.example.composecharactersproject.ui.feature.CharactersScreen.domain

import com.example.composecharactersproject.model.Character

data class CharactersState(
    val isLoading: Boolean = false,
    val listCharacter: List<Character> = arrayListOf(),
)
