package com.myisolutions.tmdbengine.util

import android.graphics.Typeface
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.myisolutions.tmdbengine.R


@BindingAdapter("url")
fun loadImage(view: ImageView, url: String?) =
    Glide.with(view)
        .load(Constants.IMG_BASE_URL + url)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(R.drawable.error)
        .into(view)

@BindingAdapter("textStyle")
fun setTypeface(view: TextView, typeface: Int) {
    view.setTypeface(null, typeface)
}