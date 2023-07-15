package com.example.rickandmorty.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.ui.character.CharacterScreen
import com.example.rickandmorty.ui.location.LocationScreen
import com.example.rickandmorty.ui.home.HomeScreen
import com.example.rickandmorty.ui.home.RickAndMortyPage

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
    navController: NavHostController,
    onPageChange: (RickAndMortyPage) -> Unit = {},
) {
    NavHost(navController = navController, startDestination = HOME) {
        composable(HOME) {
            HomeScreen(
                onCharacterClick = { navController.navigate(CHARACTER) },
                onPageChange = onPageChange
            )
        }
        composable(CHARACTER) {
            CharacterScreen(
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
