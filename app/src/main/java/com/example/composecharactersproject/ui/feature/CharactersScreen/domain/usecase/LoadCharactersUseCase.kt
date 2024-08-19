package com.example.composecharactersproject.ui.feature.CharactersScreen.domain.usecase

import com.example.composecharactersproject.model.Ability
import com.example.composecharactersproject.repository.CharacterRepository
import com.example.composecharactersproject.ui.feature.CharactersScreen.domain.CharactersResult
import com.example.composecharactersproject.util.toAbility
import com.example.composecharactersproject.util.toCharactersList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

class LoadCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {

    fun loadCharacterList(): Flow<CharactersResult> {
        return flow {
            emit(CharactersResult.Loading)
            val responseAllCharacters = characterRepository.getAllCharacters()
            if (responseAllCharacters.isSuccessful) {
                val characters = responseAllCharacters.body()?.toCharactersList() ?: emptyList()
                characters.forEach { character ->
                    character.ability =
                        characterRepository.getAbilityOfCharacter(character.name).body()
                            ?.toAbility() ?: Ability(character.name, null)
                }
                emit(CharactersResult.CharacterListLoaded(characters))
            } else {
                emit(CharactersResult.Failure())
            }
        }.catch {
            emit(CharactersResult.Failure(it))
        }
    }

}