package com.example.rickandmorty.di

import com.example.rickandmorty.api.RickAndMortyApi
import com.example.rickandmorty.data.network.RickAndMortyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiRepository(api: RickAndMortyApi) = RickAndMortyRepository(api)

}
