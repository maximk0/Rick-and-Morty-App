package com.example.rickandmorty.data.network.models

data class AllLocationDto(
    val info: InfoLocationDto,
    val results: List<LocationsDto>
)

data class LocationsDto(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)

data class InfoLocationDto(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)
