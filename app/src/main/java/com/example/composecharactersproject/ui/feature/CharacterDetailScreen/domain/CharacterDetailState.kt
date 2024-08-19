package com.example.composecharactersproject.ui.feature.CharactersScreen.domain

import com.example.composecharactersproject.model.Ability

data class CharacterDetailState(
    val ability: Ability? = null,
    val isLoading: Boolean = false
)
