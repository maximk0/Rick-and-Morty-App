package com.example.rickandmorty.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.network.models.Character
import com.example.rickandmorty.data.network.RickAndMortyRepository

class CharactersDataSource(
    private val repository: RickAndMortyRepository
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: FIRST_PAGE
        return kotlin.runCatching {
            repository.getRickAndMortyCharacters(page)
        }.fold(
            onSuccess = {
                Log.d("FAILTAG", "Characters success: $it")
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else page + 1
                )
            },
            onFailure = {
                Log.d("FAILTAG", "Characters fail: $it")
                LoadResult.Error(it)
            })
    }

    private companion object {
        private const val FIRST_PAGE = 1
    }
}
