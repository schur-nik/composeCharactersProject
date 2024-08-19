package com.example.composecharactersproject.ui.feature.CharactersScreen.domain

import com.example.composecharactersproject.model.Character

sealed class CharactersResult {

    data class CharacterListLoaded(val list: List<Character>) : CharactersResult()

    data object Loading : CharactersResult()

    data class Failure(val error: Throwable? = null) : CharactersResult()

}