package com.myisolutions.tmdbengine.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String) =
    Glide.with(view)
        .load(Constants.IMG_BASE_URL + url)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view)