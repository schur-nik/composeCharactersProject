package com.example.composecharactersproject.ui.feature.CharacterDetailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecharactersproject.ui.feature.CharacterDetailScreen.domain.CharacterDetailAction
import com.example.composecharactersproject.ui.feature.CharacterDetailScreen.domain.CharacterDetailResult
import com.example.composecharactersproject.ui.feature.CharacterDetailScreen.domain.usecase.LoadCharacterDetailUseCase
import com.example.composecharactersproject.ui.feature.CharactersScreen.domain.CharacterDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val loadCharacterDetailUseCase: LoadCharacterDetailUseCase
) : ViewModel() {

    val state = MutableStateFlow(CharacterDetailState())

    init {
        processedAction(CharacterDetailAction.Init("0"))
    }

    fun processedAction(action: CharacterDetailAction) {
        viewModelScope.launch {
            when (action) {
                is CharacterDetailAction.Init -> loadCharacterDetailUseCase.loadCharacterDetail(action.id)
                else -> null
            }?.collect { result ->
                handleResult(result)
            }
        }
    }

    private suspend fun handleResult(result: CharacterDetailResult) {
        when (result) {
            is CharacterDetailResult.Loading -> {
                state.emit(state.value.copy(isLoading = true))
            }

            is CharacterDetailResult.CharacterDetailLoaded -> {
                state.emit(
                    state.value.copy(
                        isLoading = false,
                        ability = result.ability
                    )
                )
            }

            is CharacterDetailResult.Failure -> {
                state.emit(
                    state.value.copy(
                        isLoading = false,
                    )
                )
            }
        }
    }

}