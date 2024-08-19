package com.example.composecharactersproject.ui.feature.CharacterDetailScreen.domain

sealed class CharacterDetailAction {

    data class Init(val id: String) : CharacterDetailAction()

}