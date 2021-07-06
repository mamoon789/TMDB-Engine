package com.myisolutions.tmdbengine.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.myisolutions.tmdbengine.data.model.TmdbResponse
import com.myisolutions.tmdbengine.databinding.MovieItemBinding
import com.myisolutions.tmdbengine.util.Constants.IMG_BASE_URL
import java.text.SimpleDateFormat
import java.util.*

class TmdbMovieAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<TmdbResponse.Movie, TmdbMovieAdapter.MovieViewHolder>(MOVIE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class MovieViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null)
                        listener.onItemClick(item)
                }
            }
        }

        fun bind(movie: TmdbResponse.Movie) {
            Glide.with(itemView)
                .load(IMG_BASE_URL + movie.backdrop_path)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivCover)

            binding.tvName.text = movie.original_title
            val time = SimpleDateFormat("yyyy-MM-dd").parse(movie.release_date).time
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = time
            binding.tvYear.text = "(" + calendar.get(Calendar.YEAR) + ")"
        }
    }

    interface OnItemClickListener {
        fun onItemClick(movie: TmdbResponse.Movie)
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<TmdbResponse.Movie>() {
            override fun areItemsTheSame(oldItem: TmdbResponse.Movie, newItem: TmdbResponse.Movie) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: TmdbResponse.Movie,
                newItem: TmdbResponse.Movie
            ) =
                oldItem == newItem
        }
    }
}