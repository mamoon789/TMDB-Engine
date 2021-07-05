package com.myisolutions.tmdbengine.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.myisolutions.tmdbengine.data.repository.TmdbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val repository: TmdbRepository): ViewModel() {
}