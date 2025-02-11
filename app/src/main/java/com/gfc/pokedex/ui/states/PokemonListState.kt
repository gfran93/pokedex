package com.gfc.pokedex.ui.states

import com.gfc.pokedex.domain.model.Pokemon

data class PokemonListState(
    val pokemons: List<Pokemon> = listOf(),
    val searchQuery: String = "",
)
