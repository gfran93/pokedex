package com.gfc.pokedex.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.gfc.pokedex.domain.model.PokemonType

@Entity(
    tableName = "pokemon_type",
    foreignKeys = [ForeignKey(
        entity = PokemonEntity::class,
        parentColumns = ["id"],
        childColumns = ["pokemon_id"],
        onDelete = CASCADE
    )]
)
data class PokemonTypeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "pokemon_id")
    val pokemonId: Int,
    val slot: Int,
    val type: String
)

fun PokemonTypeEntity.toPokemonType(): PokemonType = PokemonType(
    slot = slot,
    type = type,
)
