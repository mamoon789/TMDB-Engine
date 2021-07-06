package com.myisolutions.tmdbengine.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.myisolutions.tmdbengine.data.api.TmdbApi
import com.myisolutions.tmdbengine.data.model.TmdbResponse
import com.myisolutions.tmdbengine.util.Constants.API_KEY
import retrofit2.HttpException
import java.io.IOException

const val STARTING_PAGING_INDEX = 1

class TmdbPagingSource(
    private val api: TmdbApi,
    private val query: String
) : PagingSource<Int, TmdbResponse.Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TmdbResponse.Movie> {
        val position = params.key ?: STARTING_PAGING_INDEX
        return try {
            val response = api.searchMovies(API_KEY, query, position)
            val movies = response.results

            LoadResult.Page(
                data = movies,
                prevKey = if (position == STARTING_PAGING_INDEX) null else position - 1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TmdbResponse.Movie>): Int? {
        return state.anchorPosition
    }
}