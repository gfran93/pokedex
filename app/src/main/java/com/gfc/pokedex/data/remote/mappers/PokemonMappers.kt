package com.gfc.pokedex.data.remote.mappers

import com.gfc.pokedex.data.local.entities.PokemonAbilityEntity
import com.gfc.pokedex.data.local.entities.PokemonEntity
import com.gfc.pokedex.data.local.entities.PokemonTypeEntity
import com.gfc.pokedex.data.remote.model.PokemonListItem
import com.gfc.pokedex.data.remote.model.PokemonResponse
import java.util.Locale

fun PokemonListItem.toPokemonEntity(): PokemonEntity = PokemonEntity(
    id = url.extractIdFromUrl(),
    name = name.formatPokemonName(),
    weight = null,
    height = null,
    baseExperience = null,
    specie = null,
)

fun String.extractIdFromUrl(): Int = this.split("/").last { it.isNotEmpty() }.toInt()

fun String.formatPokemonName() = this.replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
}

fun PokemonResponse.toPokemonEntity(): PokemonEntity = PokemonEntity(
    id = id,
    name = name.formatPokemonName(),
    weight = weight,
    height = height,
    baseExperience = baseExperience,
    specie = species.name
)

fun PokemonResponse.toTypeEntities(): List<PokemonTypeEntity> {
    return types.map { typeResponse ->
        PokemonTypeEntity(
            pokemonId = id,
            slot = typeResponse.slot,
            type = typeResponse.type.name,
        )
    }
}

fun PokemonResponse.toAbilityEntities(): List<PokemonAbilityEntity> {
    return abilities.map { abilityResponse ->
        PokemonAbilityEntity(
            pokemonId = id,
            ability = abilityResponse.ability.name,
            isHidden = abilityResponse.isHidden,
            slot = abilityResponse.slot
        )
    }
}