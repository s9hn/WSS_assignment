package com.example.wsselixir.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("loadImageCircle")
    fun loadImageCircle(view: ImageView, url: String?) {
        Glide.with(view.context)
            .load(url)
            .transform(CircleCrop())
            .into(view)
    }
}