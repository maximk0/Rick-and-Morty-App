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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: RickAndMortyRepository
) : ViewModel() {

    init {
        Log.d(TAG, "view model init")
    }

    var pagedCharacters: Flow<PagingData<Character>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { CharactersDataSource(repository) }
    ).flow.cachedIn(viewModelScope)

    private var _character = MutableStateFlow(Character())
    val character = _character.asSharedFlow()

    private var _listOfEpisodes = MutableSharedFlow<List<EpisodesDto>>()
    val listOfEpisodes = _listOfEpisodes.asSharedFlow()

    fun getCharacter(character: Character) {
       _character.update { character ->
           character.copy(
               created = character.created,
               episode = character.episode,
               gender = character.gender,
               id = character.id,
               image = character.image,
               location = character.location,
               name = character.name,
               origin = character.origin,
               species = character.species,
               status = character.status,
               type = character.type,
               url = character.url
           )
       }
        Log.d("CharacterScreen", "character: $character")
    }

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