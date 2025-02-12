package com.gfc.pokedex.domain.repository

import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.gfc.pokedex.data.POKEMON_ID_PLACEHOLDER
import com.gfc.pokedex.data.POKEMON_IMAGE_LOCAL_FILENAME
import com.gfc.pokedex.data.local.entities.toPokemon
import com.gfc.pokedex.data.local.service.PokemonDao
import com.gfc.pokedex.data.remote.mappers.toAbilityEntities
import com.gfc.pokedex.data.remote.mappers.toPokemonEntity
import com.gfc.pokedex.data.remote.mappers.toTypeEntities
import com.gfc.pokedex.data.remote.model.PokemonListItem
import com.gfc.pokedex.data.remote.service.PokeApiService
import com.gfc.pokedex.data.worker.ImageDownloadWorker
import com.gfc.pokedex.domain.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokeApiService: PokeApiService,
    private val pokemonDao: PokemonDao,
    private val workManager: WorkManager,
    private val filesDirPath: String,
) {
    fun getAllPokemon(): Flow<List<Pokemon>> = pokemonDao
        .getAllPokemon()
        .map { pokemonEntities ->
            pokemonEntities.map { pokemonEntity ->
                pokemonEntity.toPokemon()
            }
        }

    fun getPokemonById(pokemonId: Int): Flow<Pokemon> = pokemonDao
        .getPokemonWithDetails(pokemonId)
        .filterNotNull()
        .map { pokemonWithDetails ->
            pokemonWithDetails.toPokemon().copy(
                imageFileName = "$filesDirPath/" + POKEMON_IMAGE_LOCAL_FILENAME.replace(
                    POKEMON_ID_PLACEHOLDER, pokemonId.toString()
                )
            )
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

    suspend fun fetchAndSavePokemonDetails() = withContext(Dispatchers.IO) {
        val pokemonIds = pokemonDao.getAllPokemonIds()
        pokemonIds.forEach { id ->
            val pokemonResponse = pokeApiService.getPokemonById(id)
            with(pokemonDao) {
                updatePokemon(pokemonResponse.toPokemonEntity())
                insertTypes(pokemonResponse.toTypeEntities())
                insertAbilities(pokemonResponse.toAbilityEntities())
            }
        }
    }

    suspend fun enqueuePokemonImageDownload() = withContext(Dispatchers.IO) {
        val pokemonIds = pokemonDao.getAllPokemonIds()
        pokemonIds.forEach { pokemonId ->
            val data = Data.Builder()
                .putInt("pokemonId", pokemonId)
                .build()

            val workRequest = OneTimeWorkRequestBuilder<ImageDownloadWorker>()
                .setInputData(data)
                .build()
            workManager.enqueue(workRequest)
        }
    }
}
