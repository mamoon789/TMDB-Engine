package com.myisolutions.tmdbengine.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.myisolutions.tmdbengine.R
import com.myisolutions.tmdbengine.databinding.FragmentMovieDetailBinding
import com.myisolutions.tmdbengine.ui.viewmodel.MovieDetailViewModel
import com.myisolutions.tmdbengine.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {
    private val viewModel by viewModels<MovieDetailViewModel>()

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieDetailBinding.bind(view)
        viewModel.getMovieDetailAsync(550)
        try {
            viewModel.movie.observe(viewLifecycleOwner) {
                binding.apply {
                    Glide.with(this@MovieDetailFragment)
                        .load(Constants.IMG_BASE_URL + it.backdrop_path)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(ivCover)
                    tvName.text = it.original_title
                    tvDuration.text = it.runtime.toString()
                    tvRevenue.text = "$" + it.revenue
                    tvDiscription.text = it.overview
                }
            }
        } catch (e: Exception) {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}