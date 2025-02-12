package com.gfc.pokedex.data.local.service

import com.gfc.pokedex.data.local.entities.PokemonEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PokemonEntity::class],
    version = 1,
    exportSchema = false // Set to true if you want to export schema for versioning
)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
