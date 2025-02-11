package com.gfc.pokedex.domain.repository

import com.gfc.pokedex.data.remote.mappers.toPokemon
import com.gfc.pokedex.data.remote.service.PokeApiService
import com.gfc.pokedex.domain.model.Pokemon
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokeApiService: PokeApiService
) {
    suspend fun getPokemonList(): List<Pokemon> {
        val pokemonListResponse = pokeApiService.getPokemonList(151)
        return pokemonListResponse.results.map { it.toPokemon() }
    }
}
