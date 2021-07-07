package com.myisolutions.tmdbengine.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.myisolutions.tmdbengine.R
import com.myisolutions.tmdbengine.data.model.TmdbResponse
import com.myisolutions.tmdbengine.databinding.FragmentMovieSearchBinding
import com.myisolutions.tmdbengine.ui.view.adapter.TmdbMovieAdapter
import com.myisolutions.tmdbengine.ui.viewmodel.MovieSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class MovieSearchFragment : Fragment(R.layout.fragment_movie_search),
    TmdbMovieAdapter.OnItemClickListener {
    private val viewModel by viewModels<MovieSearchViewModel>()

    private var _binding: FragmentMovieSearchBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieSearchBinding.bind(view)

        val adapter = TmdbMovieAdapter(this)

        binding.apply {
            rvMovies.setHasFixedSize(true)
            rvMovies.adapter = adapter

            svMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        rvMovies.scrollToPosition(0)
                        viewModel.searchMovies(query)
                        svMovie.clearFocus()
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }

        try {
            viewModel.movies.observe(viewLifecycleOwner) {
                adapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        } catch (e: Exception) {
        }
    }

    override fun onItemClick(movie: TmdbResponse.Movie) {
        val action = MovieSearchFragmentDirections.navigateToMovieDetailFragment(movie)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}