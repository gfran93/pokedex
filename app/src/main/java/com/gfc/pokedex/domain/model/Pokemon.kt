package com.gfc.pokedex.domain.model

data class Pokemon(
    val id: Int,
    val name: String,
    val weight: Int?,
    val height: Int?,
    val baseExperience: Int?,
    val specie: String?,
    val types: List<PokemonType>?,
    val abilities: List<PokemonAbility>?,
)
