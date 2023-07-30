package com.example.rickandmorty.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.network.RickAndMortyRepository
import com.example.rickandmorty.data.network.models.Character
import com.example.rickandmorty.data.network.models.EpisodesDto
import com.example.rickandmorty.ui.character.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: RickAndMortyRepository
) : ViewModel() {

     val characterId: Int = savedStateHandle.get<Int>(CHARACTER_ID_SAVED_STATE_KEY)!!

    private val _character = MutableStateFlow<Character?>(null)
    val character = _character.asStateFlow()

    private var _listOfEpisodes = MutableSharedFlow<List<EpisodesDto>>()
    val listOfEpisodes = _listOfEpisodes.asSharedFlow()

    init {
        getCharacter(characterId)
    }

    fun getCharacter(id: Int = characterId) {
        viewModelScope.launch {
            try {
                Log.d(TAG, "BEFORE get id character: $id, character: ${character.collect{it}}")
                _character.value =  repository.getCharacter(id)
                Log.d(TAG, "AFTER get id character: $id, character: ${character.collect{ it }}")
            } catch (e: Exception) {
                Log.e(TAG, "error from repo vm:$e")
            }
            Log.d(TAG, "get id character: $id, character: ${character.collect{ it }}")

        }
    }

    fun getEpisodes(episodes: List<String?>?) {
        if (episodes != null) {

            val numberOfEpisode = mutableListOf<String>()
            episodes.forEach {
                if (it != null) {
                    numberOfEpisode.add(it.replace(Regex("\\D"), ""))
                }
            }

            viewModelScope.launch {
                if (episodes.size == 1) {
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

    companion object {
        private const val CHARACTER_ID_SAVED_STATE_KEY = "id"
    }
}
