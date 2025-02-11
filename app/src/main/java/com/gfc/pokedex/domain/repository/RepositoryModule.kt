package com.gfc.pokedex.domain.repository

import com.gfc.pokedex.data.remote.service.PokeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePokemonRepository(
        pokeApiService: PokeApiService,
    ): PokemonRepository {
        return PokemonRepository(pokeApiService)
    }
}
