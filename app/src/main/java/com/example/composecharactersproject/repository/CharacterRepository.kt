package com.example.composecharactersproject.repository

import com.example.composecharactersproject.network.Api
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val api: Api
) {

    suspend fun getAllCharacters() = api.getAllCharacters()

    suspend fun getAbilityOfCharacter(name: String) = api.getAbilityOfCharacter(name)

}