package com.example.rickandmorty.ui.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmorty.R
import com.example.rickandmorty.data.network.models.Character
import com.example.rickandmorty.ui.charactersList.CharacterListScreen
import com.example.rickandmorty.ui.location.LocationScreen
import com.example.rickandmorty.ui.theme.Gray120
import com.example.rickandmorty.ui.theme.Gray80
import com.example.rickandmorty.ui.theme.GrayNav
import com.example.rickandmorty.viewmodels.CharactersViewModel
import kotlinx.coroutines.launch


enum class RickAndMortyPage(
    @StringRes val titleResId: Int, @DrawableRes val drawableResId: Int
) {
    ALL_CHARACTERS(
        R.string.all_characters,
        R.drawable.ic_all_characters
    ),
    LOCATION(R.string.location, R.drawable.ic_location)
}

//@Composable
//fun HomeScreen(
//    viewModel: CharactersViewModel = hiltViewModel(),
//    onCharacterClick: (Character) -> Unit,
//    onPageChange: (RickAndMortyPage) -> Unit = {},
//) {
//    HomePagerScreen(
//        onCharacterClick = {onCharacterClick(viewModel.getCharacter(it))},
//        onPageChange = onPageChange
//    )
//}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePagerScreen(
    modifier: Modifier = Modifier,
    onCharacterClick: (Character) -> Unit,
    onPageChange: (RickAndMortyPage) -> Unit,
    pages: Array<RickAndMortyPage> = RickAndMortyPage.values()
) {
    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState.currentPage) {
        onPageChange(pages[pagerState.currentPage])
    }

    Column(modifier.nestedScroll(rememberNestedScrollInteropConnection())) {
        val coroutineScope = rememberCoroutineScope()

        // Tab Row
        TabRow(selectedTabIndex = pagerState.currentPage) {
            pages.forEachIndexed { index, page ->
                val title = stringResource(id = page.titleResId)
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    text = { Text(text = title) },
                    icon = {
                        Icon(
                            painter = painterResource(id = page.drawableResId),
                            contentDescription = title,
                        )
                    },
                    unselectedContentColor = Gray120,
                    selectedContentColor = Gray80,
                    modifier = Modifier.background(GrayNav)
                )
            }
        }

        // Pages
        HorizontalPager(
            pageCount = pages.size, state = pagerState, verticalAlignment = Alignment.Top
        ) { index ->
            when (pages[index]) {
                RickAndMortyPage.ALL_CHARACTERS -> {
                    CharacterListScreen(
                        onCharacterItemClicked = { character -> onCharacterClick(character) }
                    )
                }

                RickAndMortyPage.LOCATION -> {
                    LocationScreen()
                }
            }
        }
    }
}
