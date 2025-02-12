package com.gfc.pokedex.data.remote.mappers

import com.gfc.pokedex.data.local.entities.PokemonEntity
import com.gfc.pokedex.data.remote.model.PokemonListItem
import com.gfc.pokedex.domain.model.Pokemon
import java.util.Locale

fun PokemonListItem.toPokemon(): Pokemon {
    return Pokemon(
        id = url.extractIdFromUrl(),
        name = name.formatPokemonName()
    )
}

fun PokemonListItem.toPokemonEntity(): PokemonEntity = PokemonEntity(
    id = url.extractIdFromUrl(),
    name = name.formatPokemonName(),
)

fun String.extractIdFromUrl(): Int = this.split("/").last { it.isNotEmpty() }.toInt()

fun String.formatPokemonName() = this.replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
}
