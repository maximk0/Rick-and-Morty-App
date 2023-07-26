package com.example.rickandmorty.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmorty.ui.character.CharacterScreen
import com.example.rickandmorty.ui.character.TAG
import com.example.rickandmorty.ui.home.HomePagerScreen
import com.example.rickandmorty.ui.location.LocationScreen
import com.example.rickandmorty.ui.home.RickAndMortyPage
import com.example.rickandmorty.viewmodels.CharactersViewModel

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
    viewModel: CharactersViewModel = hiltViewModel(),
    navController: NavHostController,
    onPageChange: (RickAndMortyPage) -> Unit = {},
) {
    NavHost(navController = navController, startDestination = RickAndMortyScreen.Home.name) {
        composable(route = RickAndMortyScreen.Home.name) {
            Log.d(TAG, "viewmodel: $viewModel")

            HomePagerScreen(
                onCharacterClick = { character ->
                    Log.d(TAG, "id arg: ${character.id}")
                    navController.navigate("${RickAndMortyScreen.Character.name}/${character.id}")
                },
                onPageChange = onPageChange
            )
        }
        composable(
            route = "${RickAndMortyScreen.Character.name}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->

            backStackEntry.arguments?.getString("id")?.let {
                Log.d(TAG, "id arg: $id")
                CharacterScreen(
                    viewModel = viewModel,
                    onBackClick = { navController.navigateUp() },
                    id = it
                )
            }
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