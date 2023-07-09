package com.example.rickandmorty.compose

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.compose.character.CharacterScreen
import com.example.rickandmorty.compose.location.LocationScreen
import com.example.rickandmorty.viewmodels.ChatactersModel
import com.example.rickandmorty.compose.home.HomeScreen
import com.example.rickandmorty.compose.home.RickAndMortyPage

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
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onCharacterClick = { navController.navigate("character") },
                onPageChange = onPageChange
            )
        }
        composable("character") {
            CharacterScreen(
                 onBackClick = { navController.navigateUp() }
            )
        }
        composable("location") {
            LocationScreen()
        }
    }
}
