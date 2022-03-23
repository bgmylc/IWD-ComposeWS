package com.begumyolcu.composepokemonapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.begumyolcu.composepokemonapp.ui.theme.ComposePokemonAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePokemonAppTheme {
                PokemonList(pokemons = Pokemons.pokemonList)
            }

        }
    }
}

data class Pokemon(val name: String, val type: String, val weaknesses: String, val imageId: Int)

class Pokemons {
    companion object {
        val pikachu = Pokemon("Pikachu", "Electric", "Ground", R.drawable.ic_pikachu)
        val bulbasaur = Pokemon(
            "Bulbasaur",
            "Grass, Poison",
            "Fire, Psychic, Flying, Ice",
            R.drawable.ic_bulbasaur
        )
        val squirtle = Pokemon("Squirtle", "Water", "Grass, Electric", R.drawable.ic_squirtle)
        val charmander =
            Pokemon("Charmander", "Fire", "Water, Ground, Rock", R.drawable.ic_charmander)
        val psyduck = Pokemon("Psyduck", "Water", "Grass, Electric", R.drawable.ic_psyduck)

        val pokemonList = listOf(pikachu, bulbasaur, squirtle, charmander, psyduck)
    }
}


@Composable
fun InfoCard(pokemon: Pokemon) {

    Surface(shape = MaterialTheme.shapes.medium, elevation = 3.dp) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(pokemon.imageId),
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape),
                contentDescription = "${pokemon.name} profile picture"
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = "Hello ${pokemon.name}",
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2
                )
                Spacer(modifier = Modifier.height(4.dp))

                Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
                    Text(
                        text = "Type: ${pokemon.type} \nWeaknesses: ${pokemon.weaknesses}",
                        modifier = Modifier.padding(all = 4.dp),
                        style = MaterialTheme.typography.body2
                    )
                }

            }
        }
    }
}

@Composable
fun PokemonList(pokemons: List <Pokemon>){
    LazyColumn{
        items(pokemons) { pokemon ->
            InfoCard(pokemon = pokemon)
        }
    }
}


@Preview(showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun DefaultPreview() {
    ComposePokemonAppTheme {
        PokemonList(pokemons = Pokemons.pokemonList)
    }
}