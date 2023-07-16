package com.example.rickandmorty.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.ui.character.CharacterScreen
import com.example.rickandmorty.ui.home.HomePagerScreen
import com.example.rickandmorty.ui.location.LocationScreen
import com.example.rickandmorty.ui.home.RickAndMortyPage
import com.example.rickandmorty.viewmodels.CharactersViewModel

@Composable
fun RickAndMortyApp(
    onPageChange: (RickAndMortyPage) -> Unit = {}
) {
    val navController = rememberNavController()
    RickAndMortyNavHost(
        navController = navController,
        onPageChange = onPageChange,
    )
}

@Composable
fun RickAndMortyNavHost(
    viewModel: CharactersViewModel = hiltViewModel(),
    navController: NavHostController,
    onPageChange: (RickAndMortyPage) -> Unit = {},
) {
    NavHost(navController = navController, startDestination = HOME) {
        composable(HOME) {
            HomePagerScreen(
                onCharacterClick = {
                    viewModel.getCharacter(it)
                    navController.navigate(CHARACTER)
                                   },
                onPageChange = onPageChange
            )
        }
        composable(CHARACTER) {
            CharacterScreen(
                viewModel = viewModel,
                 onBackClick = { navController.navigateUp() }
            )
        }
        composable(LOCATION) {
            LocationScreen()
        }
    }
}

const val HOME = "home"
const val CHARACTER = "character"
const val LOCATION = "location"
