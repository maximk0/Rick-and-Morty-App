package com.example.rickandmorty.ui.location

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.rickandmorty.R
import com.example.rickandmorty.ui.commonUi.ErrorColumn
import com.example.rickandmorty.ui.commonUi.LoadingBox
import com.example.rickandmorty.viewmodels.LocationViewModel

@Composable
fun LocationScreen(viewModel: LocationViewModel = hiltViewModel()) {
    val lazyLocationsItems = viewModel.locations.collectAsLazyPagingItems()

    LazyColumn {

        items(lazyLocationsItems) {
            it?.let { LocationItem(location = it) }
                ?: Text(text = stringResource(id = R.string.oops))
        }

        lazyLocationsItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        LoadingBox(modifier = Modifier.fillParentMaxSize())
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
                            loadStateError = lazyLocationsItems.loadState.refresh as LoadState.Error,
                            onClick = { retry() }
                        )
                    }
                }

                loadState.append is LoadState.Error -> {
                    item {
                        ErrorColumn(
                            modifier = Modifier.fillParentMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            loadStateError = lazyLocationsItems.loadState.append as LoadState.Error,
                            onClick = { retry() }
                        )
                    }
                }

            }
        }
    }
}
