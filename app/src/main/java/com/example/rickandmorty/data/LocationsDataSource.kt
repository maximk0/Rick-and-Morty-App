package com.example.rickandmorty.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.network.RickAndMortyRepository
import com.example.rickandmorty.data.network.models.LocationsDto

class LocationsDataSource(
    private val repository: RickAndMortyRepository
) : PagingSource<Int, LocationsDto>() {


    override fun getRefreshKey(state: PagingState<Int, LocationsDto>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationsDto> {
        val page = params.key ?: FIRST_PAGE
        return kotlin.runCatching {
            repository.getLocation(page)
        }.fold(
            onSuccess = {
                Log.d(TAG, "Location success: $it")
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else page + 1
                )

            },
            onFailure = {
                Log.d(TAG, "Location fail: $it")
                LoadResult.Error(it)
            })
    }

    private companion object {
        private const val FIRST_PAGE = 1
        private const val TAG = "LocationsDataSource"
    }
}
