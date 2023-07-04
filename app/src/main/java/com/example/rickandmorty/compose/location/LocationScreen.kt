package com.example.rickandmorty.compose.location

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmorty.presentation.MainViewModel
import androidx.paging.compose.items

@Composable
fun LocationScreen(viewModel: MainViewModel) {
    val lazyLocationsItems = viewModel.locations.collectAsLazyPagingItems()

    LazyColumn{
        items(lazyLocationsItems) {
            it?.let { LocationItem(location = it) } ?: Text(text = "Ooops")
        }

        lazyLocationsItems.apply {
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
                    val e = lazyLocationsItems.loadState.refresh as LoadState.Error
                    item {
                        Column(modifier = Modifier.fillParentMaxSize()) {
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
