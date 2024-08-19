package com.example.composecharactersproject.ui.feature.CharacterDetailScreen.domain.usecase

import com.example.composecharactersproject.model.Ability
import com.example.composecharactersproject.repository.CharacterRepository
import com.example.composecharactersproject.ui.feature.CharacterDetailScreen.domain.CharacterDetailResult
import com.example.composecharactersproject.util.toAbility
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadCharacterDetailUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {

    fun loadCharacterDetail(id: String): Flow<CharacterDetailResult> {
        return flow {
            emit(CharacterDetailResult.Loading)
            val responseCharacter = characterRepository.getAbilityOfCharacter(id)
            if (responseCharacter.isSuccessful) {
                val ability = responseCharacter.body()?.toAbility() ?: Ability(id, null)
                emit(CharacterDetailResult.CharacterDetailLoaded(ability))
            } else {
                emit(CharacterDetailResult.Failure())
            }
        }.catch {
            emit(CharacterDetailResult.Failure(it))
        }
    }

}