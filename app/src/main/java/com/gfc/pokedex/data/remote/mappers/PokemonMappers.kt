package com.gfc.pokedex.data.remote.mappers

import com.gfc.pokedex.data.remote.model.PokemonListItem
import com.gfc.pokedex.domain.model.Pokemon
import java.util.Locale

fun PokemonListItem.toPokemon(): Pokemon {
    return Pokemon(
        id = url.extractIdFromUrl(),
        name = name
            .replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
            },
    )
}

fun String.extractIdFromUrl(): Int = this.split("/").last { it.isNotEmpty() }.toInt()
