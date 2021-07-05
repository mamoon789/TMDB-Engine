package com.myisolutions.tmdbengine.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.myisolutions.tmdbengine.data.TmdbPagingSource
import com.myisolutions.tmdbengine.data.api.TmdbApi
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
            pagingSourceFactory = { TmdbPagingSource(api, query)}
        ).liveData

}