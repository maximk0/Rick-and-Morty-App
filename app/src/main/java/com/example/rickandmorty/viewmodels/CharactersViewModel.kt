package com.example.rickandmorty.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.data.CharactersDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import com.example.rickandmorty.data.network.models.Result
import com.example.rickandmorty.data.network.models.EpisodesDto
import com.example.rickandmorty.data.network.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: RickAndMortyRepository
) : ViewModel() {

    var pagedCharacters: Flow<PagingData<Result>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { CharactersDataSource(repository) }
    ).flow.cachedIn(viewModelScope)

    var character: Result? = null

    fun reloadList() {
        pagedCharacters = Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { CharactersDataSource(repository) }
        ).flow.cachedIn(viewModelScope)
    }

    private var _listOfEpisodes = MutableSharedFlow<List<EpisodesDto>>()
    val listOfEpisodes = _listOfEpisodes.asSharedFlow()

    fun getEpisodes(episodes: List<String>?) {
        viewModelScope.launch {
            val numberOfEpisode = mutableListOf<String>()

            episodes?.forEach{
                numberOfEpisode.add(it.replace(Regex("\\D"), ""))
            }
            if (episodes?.size == 1) {
                _listOfEpisodes.emit(
                    listOf(
                        repository.getEpisode(numberOfEpisode.first().toString())
                    )
                )
            } else {
                _listOfEpisodes.emit(
                    repository.getListOfEpisodes(
                        numberOfEpisode.toString()
                    )
                )
            }
        }
    }
}