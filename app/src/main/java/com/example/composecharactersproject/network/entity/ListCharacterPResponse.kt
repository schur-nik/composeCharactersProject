package com.example.composecharactersproject.network.entity

data class ListCharacterPResponse(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<CharacterP>
)