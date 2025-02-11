package com.gfc.pokedex.domain.repository

import com.gfc.pokedex.data.remote.model.PokemonListItem
import com.gfc.pokedex.data.remote.model.PokemonListResponse
import com.gfc.pokedex.data.remote.service.PokeApiService
import com.gfc.pokedex.domain.model.Pokemon
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class PokemonRepositoryTest {

    private val pokeApiService = mockk<PokeApiService>()
    private val repository = PokemonRepository(pokeApiService)

    @Test
    fun `empty list test`() = runTest {
        coEvery {
            pokeApiService.getPokemonList(any())
        } returns PokemonListResponse(results = listOf())


        val response = repository.getPokemonList()

        assertEquals(listOf<Pokemon>(), response)
        coVerify(exactly = 1) { pokeApiService.getPokemonList(any()) }
    }

    @Test
    fun `getPokemonList should return a list of Pokemon`() = runTest {
        val pokemonResponse = PokemonListResponse(
            results = listOf(
                PokemonListItem(name = "bulbasaur", url = "https://pokeapi.co/api/v2/pokemon/1/"),
                PokemonListItem(name = "ivysaur", url = "https://pokeapi.co/api/v2/pokemon/2/")
            )
        )

        coEvery { pokeApiService.getPokemonList(any()) } returns pokemonResponse

        val result = repository.getPokemonList()

        // Assert
        assertEquals(2, result.size)
        assertEquals("Bulbasaur", result[0].name)
        assertEquals("Ivysaur", result[1].name)
        coVerify(exactly = 1) { pokeApiService.getPokemonList(any()) }
    }
}
