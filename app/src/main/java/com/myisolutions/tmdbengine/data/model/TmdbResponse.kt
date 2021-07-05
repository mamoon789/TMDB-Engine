package com.myisolutions.tmdbengine.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class TmdbResponse(
    val results: List<Movie>
) {
    @Parcelize
    data class Movie(
        val id: String,
        val backdrop_path: String,
        val original_title: String,
        val overview: String,
        val poster_path: String,
        val vote_average: String,
        val vote_count: String
    ) : Parcelable
}