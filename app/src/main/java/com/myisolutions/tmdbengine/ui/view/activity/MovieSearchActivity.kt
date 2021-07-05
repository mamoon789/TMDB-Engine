package com.myisolutions.tmdbengine.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myisolutions.tmdbengine.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieSearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_search)
    }
}