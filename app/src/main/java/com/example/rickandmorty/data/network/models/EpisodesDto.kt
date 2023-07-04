package com.example.rickandmorty.data.network.models

data class EpisodesDto(
    val airDate: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)