package com.gfc.pokedex.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gfc.pokedex.domain.repository.PokemonRepository
import com.gfc.pokedex.ui.states.PokemonListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(PokemonListState())
    val state = _state.asStateFlow()

    init {
        loadPokemonList()
    }

    private fun loadPokemonList() {
        viewModelScope.launch {
            val pokemons = repository.getPokemonList()
            _state.emit(_state.value.copy(pokemons = pokemons))
        }
    }
}
