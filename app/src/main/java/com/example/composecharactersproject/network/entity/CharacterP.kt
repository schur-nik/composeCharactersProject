package com.example.composecharactersproject.network.entity

import com.google.gson.annotations.SerializedName

data class CharacterP(
    val name: String,
    @SerializedName("url")
    val abilityUrl: String
)