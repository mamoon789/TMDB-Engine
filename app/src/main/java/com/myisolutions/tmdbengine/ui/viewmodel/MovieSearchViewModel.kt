package com.myisolutions.tmdbengine.ui.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.myisolutions.tmdbengine.data.model.TmdbResponse
import com.myisolutions.tmdbengine.data.repository.TmdbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(private val repository: TmdbRepository) : ViewModel() {
    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val movies = currentQuery.switchMap {
        repository.getSearchResults(it).cachedIn(viewModelScope)
    }

    fun searchMovies(query: String) {
        currentQuery.value = query
    }

    fun getMovieDetail(id: Int){

    }

    companion object{
        private const val DEFAULT_QUERY = "fight"
    }
}