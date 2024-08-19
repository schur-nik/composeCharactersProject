package com.example.composecharactersproject.util

import com.example.composecharactersproject.model.Ability
import com.example.composecharactersproject.model.Character
import com.example.composecharactersproject.model.Sprites
import com.example.composecharactersproject.network.entity.AbilityPResponse
import com.example.composecharactersproject.network.entity.ListCharacterPResponse
import com.example.composecharactersproject.network.entity.SpritesP

fun ListCharacterPResponse.toCharactersList() : List<Character> {
    return this.results.map { character ->
        Character(
            character.name,
            null
        )
    }
}

fun AbilityPResponse.toAbility() : Ability {
    return Ability(this.name, this.sprites.toSprite())
}

fun SpritesP.toSprite() : Sprites {
    return Sprites(this.front_default, this.back_default)
}