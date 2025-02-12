package com.gfc.pokedex.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.gfc.pokedex.domain.model.PokemonAbility

@Entity(
    tableName = "pokemon_ability",
    foreignKeys = [ForeignKey(
        entity = PokemonEntity::class,
        parentColumns = ["id"],
        childColumns = ["pokemon_id"],
        onDelete = CASCADE
    )]
)
data class PokemonAbilityEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "pokemon_id")
    val pokemonId: Int,
    val ability: String,
    @ColumnInfo(name = "is_hidden")
    val isHidden: Boolean,
    val slot: Int,
)

fun PokemonAbilityEntity.toPokemonAbility() = PokemonAbility(
    ability = ability,
    isHidden = isHidden,
    slot = slot,
)