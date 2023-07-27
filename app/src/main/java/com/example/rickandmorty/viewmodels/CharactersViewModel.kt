package com.example.rickandmorty.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.data.CharactersDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import com.example.rickandmorty.data.network.models.Character
import com.example.rickandmorty.data.network.models.EpisodesDto
import com.example.rickandmorty.data.network.RickAndMortyRepository
import com.example.rickandmorty.ui.character.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: RickAndMortyRepository
) : ViewModel() {

    var pagedCharacters: Flow<PagingData<Character>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { CharactersDataSource(repository) }
    ).flow.cachedIn(viewModelScope)

}
