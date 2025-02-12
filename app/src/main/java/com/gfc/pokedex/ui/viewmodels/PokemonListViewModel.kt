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
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository,
    private val coroutineExceptionHandler: CoroutineExceptionHandler,
) : ViewModel() {
    private val _state = MutableStateFlow(PokemonListState())
    val state = _state.asStateFlow()

    private val searchTermFlow = MutableStateFlow(_state.value.searchTerm)

    init {
        loadPokemonList()
        fetchAndSavePokemon()
    }

    private fun launchWithExceptionHandler(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(coroutineExceptionHandler, block = block)
    }

    private fun loadPokemonList() = launchWithExceptionHandler {
        repository.getAllPokemon()
            .combine(searchTermFlow) { pokemonList, searchTerm ->
                if (searchTerm.isBlank()) {
                    pokemonList
                } else {
                    pokemonList.filter { pokemon ->
                        pokemon.name.contains(searchTerm, ignoreCase = true)
                    }
                }
            }
            .collect { pokemonList ->
                _state.emit(_state.value.copy(pokemons = pokemonList))
            }
    }

    private fun fetchAndSavePokemon() = launchWithExceptionHandler {
        repository.fetchAndSavePokemonList()
    }

    fun searchTermUpdated(newSearchTerm: String) {
        _state.value = _state.value.copy(
            searchTerm = newSearchTerm,
        )
        searchTermFlow.value = newSearchTerm
    }
}
