package com.gfc.pokedex.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gfc.pokedex.domain.model.Pokemon

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val weight: Int?,
    val height: Int?,
    @ColumnInfo("base_experience")
    val baseExperience: Int?,
    val specie: String?,
)

fun PokemonEntity.toPokemon(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        weight = weight,
        height = height,
        baseExperience = baseExperience,
        specie = specie,
        types = null,
        abilities = null,
        imageFileName = null,
    )
}
