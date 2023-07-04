package com.example.rickandmorty.data.network

import android.util.Log
import com.example.rickandmorty.api.RetrofitInstance
import com.example.rickandmorty.data.network.models.LocationsDto
import com.example.rickandmorty.data.network.models.EpisodesDto
import com.example.rickandmorty.data.network.models.Result

class RickAndMortyCharactersRepository  {

    suspend fun getRickAndMortyCharacters(page: Int): List<Result> {
        Log.d("REPOSITORY", "${RetrofitInstance.rickAndMortyApi.loadList(page).results}")
        return RetrofitInstance.rickAndMortyApi.loadList(page).results
    }

    suspend fun getEpisode(id: String) : EpisodesDto {
        Log.d("REPOSITORY", "${RetrofitInstance.rickAndMortyApi.getEpisode(id)}")
        return RetrofitInstance.rickAndMortyApi.getEpisode(id)
    }

    suspend fun getListOfEpisodes(id: String): List<EpisodesDto> {
        Log.d("REPOSITORY", "${RetrofitInstance.rickAndMortyApi.getEpisodeList(id)}")
        return RetrofitInstance.rickAndMortyApi.getEpisodeList(id)
    }

    suspend fun getLocation(page: Int): List<LocationsDto> {
        return RetrofitInstance.rickAndMortyApi.getLocationList(page).results
    }
}
