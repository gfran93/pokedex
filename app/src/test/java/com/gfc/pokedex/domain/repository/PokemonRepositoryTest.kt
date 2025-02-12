package com.gfc.pokedex.domain.repository

import androidx.work.WorkManager
import com.gfc.pokedex.data.local.entities.PokemonEntity
import com.gfc.pokedex.data.local.entities.PokemonWithDetails
import com.gfc.pokedex.data.local.entities.toPokemon
import com.gfc.pokedex.data.local.service.PokemonDao
import com.gfc.pokedex.data.remote.service.PokeApiService
import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokemonRepositoryTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private val pokeApiService: PokeApiService = mockk()
    private val pokemonDao: PokemonDao = mockk()
    private val workManager: WorkManager = mockk()

    private val filesDirPath = "/path/to/files"
    private val pokemonEntities = listOf(
        PokemonEntity(
            id = 1,
            name = "Bulbasaur",
            weight = 69,
            height = 7,
            baseExperience = 64,
            specie = "Seed"
        ),
        PokemonEntity(
            id = 2,
            name = "Ivysaur",
            weight = 130,
            height = 10,
            baseExperience = 142,
            specie = "Seed"
        ),
        PokemonEntity(
            id = 3,
            name = "Venusaur",
            weight = 1000,
            height = 20,
            baseExperience = 263,
            specie = "Seed"
        )
    )

    private lateinit var pokemonRepository: PokemonRepository

    @Before
    fun setUp() {
        pokemonRepository = PokemonRepository(pokeApiService, pokemonDao, workManager, filesDirPath)
    }

    @Test
    fun `getAllPokemon should return Flow of Pokemon from DAO`() = runTest {
        val expectedPokemon = pokemonEntities.map { it.toPokemon() }

        every { pokemonDao.getAllPokemon() } returns flowOf(pokemonEntities)

        val result = pokemonRepository.getAllPokemon().first()

        Assert.assertEquals(expectedPokemon, result)
        verify { pokemonDao.getAllPokemon() }
    }

    @Test
    fun `getPokemonById should return Flow of Pokemon`() = runTest {
        val pokemonId = 1
        val pokemonWithDetails = PokemonWithDetails(
            pokemon = pokemonEntities.first(),
            types = emptyList(),
            abilities = emptyList(),
        )
        val expectedPokemon = pokemonWithDetails.toPokemon().copy(
            imageFileName = "/path/to/files/pokemon_1.png"
        )

        every { pokemonDao.getPokemonWithDetails(pokemonId) } returns flowOf(pokemonWithDetails)

        val result = pokemonRepository.getPokemonById(pokemonId).first()

        Assert.assertEquals(expectedPokemon, result)
        verify { pokemonDao.getPokemonWithDetails(pokemonId) }
    }
}
