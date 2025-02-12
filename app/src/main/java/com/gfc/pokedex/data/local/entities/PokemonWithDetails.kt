package com.gfc.pokedex.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.gfc.pokedex.domain.model.Pokemon

data class PokemonWithDetails(
    @Embedded
    val pokemon: PokemonEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "pokemon_id"
    )
    val types: List<PokemonTypeEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "pokemon_id"
    )
    val abilities: List<PokemonAbilityEntity>
)

fun PokemonWithDetails.toPokemon(): Pokemon = pokemon
    .toPokemon()
    .copy(
        types = this.types.map { it.toPokemonType() },
        abilities = this.abilities.map { it.toPokemonAbility() },
    )
