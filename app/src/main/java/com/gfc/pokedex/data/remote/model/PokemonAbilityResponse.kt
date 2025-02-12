package com.gfc.pokedex.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonAbilityResponse(
    val ability: AbilityResponse,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    val slot: Int,
)
