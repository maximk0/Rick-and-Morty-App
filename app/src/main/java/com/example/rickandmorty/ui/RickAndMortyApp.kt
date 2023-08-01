package com.example.rickandmorty.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmorty.ui.character.CharacterScreen
import com.example.rickandmorty.ui.home.HomePagerScreen
import com.example.rickandmorty.ui.location.LocationScreen
import com.example.rickandmorty.ui.home.RickAndMortyPage

enum class RickAndMortyScreen {
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
    navController: NavHostController,
    onPageChange: (RickAndMortyPage) -> Unit = {},
) {
    NavHost(navController = navController, startDestination = RickAndMortyScreen.Home.name) {

        composable(route = RickAndMortyScreen.Home.name) {
            HomePagerScreen(
                onCharacterClick = { character ->
                    navController.navigate("${RickAndMortyScreen.Character.name}/${character.id}")
                },
                onPageChange = onPageChange
            )
        }

        composable(
            route = "${RickAndMortyScreen.Character.name}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            CharacterScreen(
                onBackClick = { navController.navigateUp() }
            )
        }

        composable(route = RickAndMortyScreen.Location.name) {
            LocationScreen()
        }

    }
}
