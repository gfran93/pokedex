package com.gfc.pokedex.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    @SerializedName("base_experience")
    val baseExperience: Int,
    val types: List<PokemonTypeResponse>,
    val species: PokemonSpecieResponse,
    val abilities: List<PokemonAbilityResponse>
)
