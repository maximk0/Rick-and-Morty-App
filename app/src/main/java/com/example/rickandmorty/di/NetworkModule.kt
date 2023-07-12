package com.example.rickandmorty.di

import com.example.rickandmorty.api.RickAndMortyApi
import com.example.rickandmorty.data.network.RickAndMortyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRickAndMortyApi(): RickAndMortyApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(RickAndMortyApi::class.java)

    @Singleton
    @Provides
    fun provideApiRepository(api: RickAndMortyApi) = RickAndMortyRepository(api)

    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/api/"
    }

}
