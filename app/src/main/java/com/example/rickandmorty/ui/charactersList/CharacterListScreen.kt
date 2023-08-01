package com.example.rickandmorty.ui.charactersList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.items
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmorty.R
import com.example.rickandmorty.ui.commonUi.LoadingBox
import com.example.rickandmorty.data.network.models.Character
import com.example.rickandmorty.ui.commonUi.ErrorColumn
import com.example.rickandmorty.viewmodels.CharactersViewModel

@Composable
fun CharacterListScreen(
    viewModel: CharactersViewModel = hiltViewModel(),
    onCharacterItemClicked: (Character) -> Unit = {},
) {
    val characterItems = viewModel.pagedCharacters.collectAsLazyPagingItems()

    LazyColumn {
        items(characterItems) {
            it?.let { character ->
                CharacterItem(
                    character = character,
                    onCharacterItemClicked = { onCharacterItemClicked(character) }
                )
            } ?: Text(text = stringResource(R.string.oops))
        }

        characterItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        LoadingBox(modifier = Modifier.fillParentMaxSize())

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
                        LoadingBox(modifier = Modifier.fillMaxWidth())
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    item {
                        ErrorColumn(
                            modifier = Modifier.fillParentMaxSize(),
                            loadStateError = characterItems.loadState.refresh as LoadState.Error,
                            onClick = { retry() }
                        )
                    }
                }

                loadState.append is LoadState.Error -> {
                    item {
                        ErrorColumn(
                            modifier = Modifier.fillParentMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            loadStateError = characterItems.loadState.append as LoadState.Error,
                            onClick = { retry() }
                        )
                    }
                }

            }
        }
    }
}
