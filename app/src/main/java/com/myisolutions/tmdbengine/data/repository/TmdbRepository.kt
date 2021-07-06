package com.myisolutions.tmdbengine.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.myisolutions.tmdbengine.data.TmdbPagingSource
import com.myisolutions.tmdbengine.data.api.TmdbApi
import com.myisolutions.tmdbengine.data.model.TmdbResponse
import com.myisolutions.tmdbengine.util.Constants.API_KEY
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TmdbRepository @Inject constructor(private val api: TmdbApi) {

    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                TmdbPagingSource(api, query)
            }
        ).liveData

    suspend fun getMovieDetail(id: Int): TmdbResponse.Movie =
        api.getMovieDetail(id, API_KEY)

}