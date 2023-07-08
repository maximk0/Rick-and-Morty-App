package com.example.rickandmorty.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.data.LocationsDataSource
import com.example.rickandmorty.data.network.RickAndMortyRepository
import com.example.rickandmorty.data.network.models.LocationsDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) : ViewModel() {

    var locations: Flow<PagingData<LocationsDto>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { LocationsDataSource(rickAndMortyRepository) }
    ).flow.cachedIn(viewModelScope)

}
