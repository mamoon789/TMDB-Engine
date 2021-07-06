package com.myisolutions.tmdbengine.ui.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.myisolutions.tmdbengine.data.model.TmdbResponse
import com.myisolutions.tmdbengine.data.repository.TmdbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val repository: TmdbRepository) : ViewModel() {
    val movie: MutableLiveData<TmdbResponse.Movie> = MutableLiveData()

    fun getMovieDetailAsync(id: Int) = viewModelScope.async {
        try {
            movie.postValue(repository.getMovieDetail(id))
        } catch (e: Exception) {
            movie.postValue(null)
        }
    }
}