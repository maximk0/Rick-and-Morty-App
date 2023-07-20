package com.example.rickandmorty.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.ui.character.CharacterScreen
import com.example.rickandmorty.ui.character.TAG
import com.example.rickandmorty.ui.home.HomePagerScreen
import com.example.rickandmorty.ui.location.LocationScreen
import com.example.rickandmorty.ui.home.RickAndMortyPage
import com.example.rickandmorty.viewmodels.CharactersViewModel
import com.google.gson.internal.bind.TypeAdapters.CHARACTER

enum class RickAndMortyScreen{
    Home,
    Character,
    Location
}

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
    NavHost(navController = navController, startDestination = RickAndMortyScreen.Home.name) {
        composable(route = RickAndMortyScreen.Home.name) {
            Log.d(TAG, "viewmodel: $viewModel")
            HomePagerScreen(
                onCharacterClick = {
                    viewModel.getCharacter(it)
                    navController.navigate(RickAndMortyScreen.Character.name)
                },
                onPageChange = onPageChange
            )
        }
        composable(route = RickAndMortyScreen.Character.name) {
            Log.d(TAG, "viewmodel: $viewModel")
            CharacterScreen(
                viewModel = viewModel,
                onBackClick = { navController.navigateUp() }
            )
        }
        composable(route = RickAndMortyScreen.Location.name) {
            LocationScreen()
        }
    }
}

data class RickAndMortyPage(
    val titleResId: Int,
    val drawableResId: Int
)