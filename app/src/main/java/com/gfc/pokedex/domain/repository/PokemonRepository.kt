package com.gfc.pokedex.domain.repository

import com.gfc.pokedex.data.local.service.PokemonDao
import com.gfc.pokedex.data.remote.mappers.toPokemonEntity
import com.gfc.pokedex.data.remote.model.PokemonListItem
import com.gfc.pokedex.data.remote.service.PokeApiService
import com.gfc.pokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.gfc.pokedex.data.local.entities.toPokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokeApiService: PokeApiService,
    private val pokemonDao: PokemonDao,
) {
    fun getAllPokemon(): Flow<List<Pokemon>> = pokemonDao
        .getAllPokemon()
        .map { pokemonEntities ->
            pokemonEntities.map { pokemonEntity ->
                pokemonEntity.toPokemon()
            }
        }

    suspend fun fetchAndSavePokemonList() = withContext(Dispatchers.IO) {
        fetchRemotePokemon()
            .map { pokemonListItem ->
                pokemonListItem.toPokemonEntity()
            }.also { pokemonEntities ->
                pokemonDao.insertPokemonList(pokemonEntities)
            }
    }

    private suspend fun fetchRemotePokemon(): List<PokemonListItem> = withContext(Dispatchers.IO) {
        val pokemonListResponse = pokeApiService.getPokemonList(151)
        return@withContext pokemonListResponse.results
    }
}
