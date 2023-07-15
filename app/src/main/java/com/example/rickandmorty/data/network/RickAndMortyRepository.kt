package com.example.rickandmorty.data.network

import android.util.Log
import com.example.rickandmorty.api.RickAndMortyApi
import com.example.rickandmorty.data.network.models.LocationsDto
import com.example.rickandmorty.data.network.models.EpisodesDto
import com.example.rickandmorty.data.network.models.Character
import javax.inject.Inject

class RickAndMortyRepository @Inject constructor(
    private val rickAndMortyApi : RickAndMortyApi
)  {

    suspend fun getRickAndMortyCharacters(page: Int): List<Character> {
        Log.d(TAG, "${rickAndMortyApi.loadList(page).results}")
        return rickAndMortyApi.loadList(page).results
    }

    suspend fun getEpisode(id: String) : EpisodesDto {
        Log.d(TAG, "${rickAndMortyApi.getEpisode(id)}")
        return rickAndMortyApi.getEpisode(id)
    }

    suspend fun getListOfEpisodes(id: String): List<EpisodesDto> {
        Log.d(TAG, "${rickAndMortyApi.getEpisodeList(id)}")
        return rickAndMortyApi.getEpisodeList(id)
    }

    suspend fun getLocation(page: Int): List<LocationsDto> {
        return rickAndMortyApi.getLocationList(page).results
    }

    companion object {
        private const val TAG = "RickAndMortyRepository"
    }
}
