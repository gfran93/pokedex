package com.gfc.pokedex.domain.repository

import android.content.Context
import androidx.work.WorkManager
import com.gfc.pokedex.data.local.service.PokemonDao
import com.gfc.pokedex.data.remote.service.PokeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePokemonRepository(
        pokeApiService: PokeApiService,
        pokemonDao: PokemonDao,
        @ApplicationContext context: Context,
    ): PokemonRepository = PokemonRepository(
        pokeApiService = pokeApiService,
        pokemonDao = pokemonDao,
        workManager = WorkManager.getInstance(context),
        filesDirPath = context.filesDir.absolutePath
    )
}
