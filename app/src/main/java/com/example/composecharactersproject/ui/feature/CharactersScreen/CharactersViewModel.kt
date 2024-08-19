package com.example.composecharactersproject.ui.feature.CharactersScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecharactersproject.ui.feature.CharactersScreen.domain.CharactersAction
import com.example.composecharactersproject.ui.feature.CharactersScreen.domain.CharactersResult
import com.example.composecharactersproject.ui.feature.CharactersScreen.domain.CharactersState
import com.example.composecharactersproject.ui.feature.CharactersScreen.domain.usecase.LoadCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val loadCharactersUseCase: LoadCharactersUseCase
) : ViewModel() {

    val state = MutableStateFlow(CharactersState())

    init {
        processedAction(CharactersAction.Init)
    }

    private fun processedAction(action: CharactersAction) {
        viewModelScope.launch {
            when (action) {
                is CharactersAction.Init -> loadCharactersUseCase.loadCharacterList()
            }.collect { result ->
                handleResult(result)
            }
        }

    }

    private suspend fun handleResult(result: CharactersResult) {
        when (result) {
            is CharactersResult.Loading -> {
                state.emit(state.value.copy(isLoading = true))
            }

            is CharactersResult.CharacterListLoaded -> {
                state.emit(
                    state.value.copy(
                        isLoading = false,
                        listCharacter = result.list
                    )
                )
            }

            is CharactersResult.Failure -> {
                state.emit(
                    state.value.copy(
                        isLoading = false,
                    )
                )
            }
        }
    }
}
