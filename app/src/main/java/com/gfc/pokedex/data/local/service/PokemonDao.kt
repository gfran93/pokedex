package com.gfc.pokedex.data.local.service

import com.gfc.pokedex.data.local.entities.PokemonEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gfc.pokedex.data.local.entities.PokemonAbilityEntity
import com.gfc.pokedex.data.local.entities.PokemonTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(pokemonList: List<PokemonEntity>)

    @Update
    suspend fun updatePokemon(pokemon: PokemonEntity)

    @Query("SELECT * FROM pokemon")
    fun getAllPokemon(): Flow<List<PokemonEntity>>

    @Query("SELECT * FROM pokemon WHERE id = :id")
    fun getPokemonById(id: Int): Flow<PokemonEntity?>

    @Query("SELECT id FROM pokemon")
    suspend fun getAllPokemonIds(): List<Int>

    @Query("DELETE FROM pokemon")
    suspend fun clearAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTypes(types: List<PokemonTypeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbilities(abilities: List<PokemonAbilityEntity>)
}
