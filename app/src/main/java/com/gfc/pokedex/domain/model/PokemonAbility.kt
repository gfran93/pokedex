package com.gfc.pokedex.domain.model

data class PokemonAbility(
    val ability: String,
    val isHidden: Boolean,
    val slot: Int,
)
