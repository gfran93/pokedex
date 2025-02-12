package com.gfc.pokedex.data.local.service

import com.gfc.pokedex.data.local.entities.PokemonEntity
import androidx.room.Database
import androidx.room.RoomDatabase
import com.gfc.pokedex.data.local.entities.PokemonAbilityEntity
import com.gfc.pokedex.data.local.entities.PokemonTypeEntity

@Database(
    entities = [PokemonEntity::class, PokemonTypeEntity::class, PokemonAbilityEntity::class],
    version = 1,
)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
