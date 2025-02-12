package com.gfc.pokedex.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.gfc.pokedex.R
import com.gfc.pokedex.domain.model.Pokemon

@Composable
fun PokemonDetailsContent(pokemon: Pokemon) = Column {
    with(pokemon) {
        if (imageFileName != null) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemon.imageFileName)
                    .build(),
                contentDescription = "Image of $name",
                modifier = Modifier
                    .size(200.dp),
                contentScale = ContentScale.Fit,
            )
        }
        Text(text = stringResource(R.string.pokemon_name, name))
        Text(text = stringResource(R.string.number, id))

        if (weight != null) {
            Text(text = stringResource(R.string.pokemon_weight, weight))
        }

        if (height != null) {
            Text(text = stringResource(R.string.pokemon_height, height))
        }

        if (baseExperience != null) {
            Text(
                text = stringResource(
                    R.string.pokemon_base_experience,
                    baseExperience
                )
            )
        }

        if (specie != null) {
            Text(text = stringResource(R.string.pokemon_species, specie))
        }

        types?.forEach { type ->
            Text(text = stringResource(R.string.pokemon_type, type.type, type.slot))
        }

        abilities?.forEach { ability ->
            val isHiddenText =
                stringResource(if (ability.isHidden) R.string.visible else R.string.invisible)
            Text(
                text = stringResource(
                    R.string.pokemon_ability,
                    ability.ability,
                    ability.slot,
                    isHiddenText,
                )
            )
        }
    }
}

@Composable
@Preview
fun PokemonDetailsContentPreview() = PokemonDetailsContent(
    Pokemon(
        id = 25,
        name = "pikachu",
        weight = 60,
        height = 4,
        baseExperience = 112,
        specie = "Mouse Pokémon",
        abilities = null,
        types = null,
        imageFileName = null,
    )
)
