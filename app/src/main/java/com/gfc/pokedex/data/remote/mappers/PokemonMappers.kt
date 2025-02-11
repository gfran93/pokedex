package com.gfc.pokedex.data.remote.mappers

import com.gfc.pokedex.data.remote.model.PokemonListItem
import com.gfc.pokedex.domain.model.Pokemon

fun PokemonListItem.toPokemon(): Pokemon {
    return Pokemon(
        id = this.url.extractIdFromUrl(),
        name = this.name,
    )
}

fun String.extractIdFromUrl(): Int = this.split("/").last { it.isNotEmpty() }.toInt()
