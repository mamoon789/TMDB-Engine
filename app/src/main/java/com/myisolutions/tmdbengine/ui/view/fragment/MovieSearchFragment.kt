package com.myisolutions.tmdbengine.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.myisolutions.tmdbengine.R
import com.myisolutions.tmdbengine.databinding.FragmentMovieDetailBinding
import com.myisolutions.tmdbengine.databinding.FragmentMovieSearchBinding
import com.myisolutions.tmdbengine.ui.view.TmdbMovieAdapter
import com.myisolutions.tmdbengine.ui.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieSearchFragment : Fragment(R.layout.activity_movie_search) {
    private val viewModel = viewModels<MovieDetailViewModel>()

    private var _binding: FragmentMovieSearchBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieSearchBinding.bind(view)

        val adapter = TmdbMovieAdapter()
        binding.rvMovies.setHasFixedSize(true)
        binding.rvMovies.adapter = adapter

        viewModel.value.movies.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}