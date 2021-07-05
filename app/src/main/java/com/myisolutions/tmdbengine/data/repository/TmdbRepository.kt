package com.myisolutions.tmdbengine.data.repository

import com.myisolutions.tmdbengine.data.api.TmdbApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TmdbRepository @Inject constructor(private val api: TmdbApi) {

}