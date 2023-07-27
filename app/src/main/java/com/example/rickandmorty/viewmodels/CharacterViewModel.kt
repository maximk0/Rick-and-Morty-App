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
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: RickAndMortyRepository
) : ViewModel() {

    private val characterId: String = checkNotNull(savedStateHandle["id"])

    private val _character = MutableStateFlow<Character?>(null)
    val character = _character.asStateFlow()

    private var _listOfEpisodes = MutableSharedFlow<List<EpisodesDto>>()
    val listOfEpisodes = _listOfEpisodes.asSharedFlow()

    init {
        Log.d(TAG, "init id arg: $characterId")
        getCharacter(characterId)
    }

    private fun getCharacter(id: String) {
        viewModelScope.launch {
            try {
                _character.value =  repository.getCharacter(id)
            } catch (e: Exception) {
                Log.e(TAG, "error from repo vm:$e")
            }
            Log.d(TAG, "get character: $id")

        }
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
