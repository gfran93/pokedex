package com.gfc.pokedex.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gfc.pokedex.domain.model.Pokemon

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val name: String,
)

fun PokemonEntity.toPokemon(): Pokemon {
    return Pokemon(
        id = this.id,
        name = this.name,
    )
}
