package com.example.rickandmorty.api

import com.example.rickandmorty.data.network.models.AllLocationDto
import com.example.rickandmorty.data.network.models.EpisodesDto
import com.example.rickandmorty.data.network.models.CharactersList
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("/api/character?")
    suspend fun loadList(@Query("page") page: Int): CharactersList

    @GET("/api/episode/{id}")
    suspend fun getEpisode(@Path("id") id: String): EpisodesDto

    @GET("/api/episode/{id}")
    suspend fun getEpisodeList(@Path("id") id: String): List<EpisodesDto>

    @GET("/api/location")
    suspend fun getLocationList(@Query("page") page: Int): AllLocationDto
}
