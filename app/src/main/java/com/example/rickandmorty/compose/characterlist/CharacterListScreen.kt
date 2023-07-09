package com.example.rickandmorty.compose.characterlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.items
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmorty.viewmodels.ChatactersModel
import com.example.rickandmorty.data.network.models.Result

@Composable
fun CharacterListScreen(
    viewModel: ChatactersModel =  hiltViewModel(),
    onCharacterItemClicked: (Result) -> Unit = {},
) {
    val characterItems = viewModel.pagedCharacters.collectAsLazyPagingItems()

    LazyColumn {
        items(characterItems) {
            it?.let {character->
                CharacterItem(
                    viewModel = viewModel,
                    character = character,
                    onCharacterItemClicked = { onCharacterItemClicked(character) }
                )
            } ?: Text(text = "Ooops")
        }
        characterItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = characterItems.loadState.refresh as LoadState.Error
                    item {
                        Column(modifier = Modifier.fillParentMaxSize()) {
                            e.error.localizedMessage?.let { Text(text = it) }
                            Button(onClick = { retry() }) {
                                Text(text = "Retry")
                            }
                        }

                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = characterItems.loadState.append as LoadState.Error
                    item {
                        Column(
                            modifier = Modifier.fillParentMaxSize(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            e.error.localizedMessage?.let { Text(text = it) }
                            Button(onClick = { retry() }) {
                                Text(text = "Retry")
                            }
                        }
                    }
                }
            }
        }
    }
}
