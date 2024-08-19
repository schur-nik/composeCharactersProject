package com.example.composecharactersproject.network

import com.example.composecharactersproject.network.entity.AbilityPResponse
import com.example.composecharactersproject.network.entity.ListCharacterPResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("pokemon?limit=5&offset=0")
    suspend fun getAllCharacters() : Response<ListCharacterPResponse>

    @GET("pokemon/{name}")
    suspend fun getAbilityOfCharacter(
        @Path("name") id: String
    ): Response<AbilityPResponse>

}