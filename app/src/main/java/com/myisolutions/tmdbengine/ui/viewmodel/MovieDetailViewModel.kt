package com.myisolutions.tmdbengine.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.myisolutions.tmdbengine.data.model.TmdbResponse
import com.myisolutions.tmdbengine.data.repository.TmdbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val repository: TmdbRepository) :
    ViewModel() {

    lateinit var movies: LiveData<PagingData<TmdbResponse.Movie>>

    fun searchMovies(query: String) {
        movies = repository.getSearchResults(query)
    }
}