package com.gfc.pokedex.data.remote.service

import com.gfc.pokedex.data.remote.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int): PokemonListResponse
}
