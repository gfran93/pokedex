package com.gfc.pokedex.ui

sealed class Screens(val route: String) {
    data object PokemonList : Screens("pokemonList")
    data object PokemonDetails: Screens("pokemon_details/{pokemonId}") {
        fun createRoute(pokemonId: Int) = "pokemon_details/$pokemonId"
    }
}
