package com.gfc.pokedex.ui.viewmodels

import com.gfc.pokedex.domain.repository.PokemonRepository
import com.gfc.pokedex.ui.states.PokemonDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val repository: PokemonRepository,
    coroutineExceptionHandler: CoroutineExceptionHandler,
) : BaseViewModel(coroutineExceptionHandler) {
    private val _state = MutableStateFlow(PokemonDetailsState())
    val state = _state.asStateFlow()

    fun setPokemonIdForDetails(pokemonId: Int) = launchWithExceptionHandler{
        repository.getPokemonById(pokemonId).collect { pokemon ->
            _state.emit(
                PokemonDetailsState(
                    pokemon = pokemon,
                    isLoading = false,
                )
            )
        }
    }
}
