package com.gfc.pokedex.ui.states

import com.gfc.pokedex.domain.model.Pokemon

data class PokemonDetailsState(
    val pokemon: Pokemon? = null,
    val isLoading: Boolean = true,
)
