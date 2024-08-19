package com.example.composecharactersproject.ui.feature.CharacterDetailScreen.domain

import com.example.composecharactersproject.model.Ability

sealed class CharacterDetailResult {

    data class CharacterDetailLoaded(val ability: Ability) : CharacterDetailResult()

    data object Loading : CharacterDetailResult()

    data class Failure(val error: Throwable? = null) : CharacterDetailResult()

}