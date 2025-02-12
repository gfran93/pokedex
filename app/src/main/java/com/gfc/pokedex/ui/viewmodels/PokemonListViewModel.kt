package com.gfc.pokedex.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gfc.pokedex.domain.repository.PokemonRepository
import com.gfc.pokedex.ui.states.PokemonListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository,
    private val coroutineExceptionHandler: CoroutineExceptionHandler,
) : ViewModel() {
    private val _state = MutableStateFlow(PokemonListState())
    val state = _state.asStateFlow()

    init {
        loadPokemonList()
        fetchAndSavePokemon()
    }

    private fun launchWithExceptionHandler(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(coroutineExceptionHandler, block = block)
    }

    private fun loadPokemonList() = launchWithExceptionHandler {
        repository.getAllPokemon().collect { pokemonList ->
            _state.emit(_state.value.copy(pokemons = pokemonList))
        }
    }

    private fun fetchAndSavePokemon() = launchWithExceptionHandler {
        repository.fetchAndSavePokemonList()
    }
}
