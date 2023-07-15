//package com.example.rickandmorty.ui.commonUi
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.LazyItemScope
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.stringResource
//import androidx.paging.LoadState
//import com.example.rickandmorty.R
//import com.example.rickandmorty.ui.location.LocationItem
//
//@Composable
//fun LazyColumnWithStates(
//    listOfItems: List<Any>,
//    items: LazyItemScope
//) {
//    LazyColumn {
//        items(listOfItems) {
//            it?.let { LocationItem(location = it) }
//                ?: Text(text = stringResource(id = R.string.oops))
//        }
//
//        listOfItems.apply {
//            when {
//                loadState.refresh is LoadState.Loading -> {
//                    item {
//                        LoadingBox(modifier = Modifier.fillParentMaxSize())
//                    }
//                }
//
//                loadState.append is LoadState.Loading -> {
//                    item {
//                        LoadingBox(modifier = Modifier.fillMaxWidth())
//                    }
//                }
//
//                loadState.refresh is LoadState.Error -> {
//                    item {
//                        ErrorColumn(
//                            modifier = Modifier.fillParentMaxSize(),
//                            loadStateError = listOfItems.loadState.refresh as LoadState.Error,
//                            onClick = { retry() }
//                        )
//                    }
//                }
//
//                loadState.append is LoadState.Error -> {
//                    item {
//                        ErrorColumn(
//                            modifier = Modifier.fillParentMaxSize(),
//                            verticalArrangement = Arrangement.Center,
//                            loadStateError = listOfItems.loadState.append as LoadState.Error,
//                            onClick = { retry() }
//                        )
//                    }
//                }
//
//            }
//        }
//    }
//}