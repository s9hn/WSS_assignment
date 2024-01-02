package com.example.wsselixir.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImageCircle(view: ImageView, url: String?) {
        Glide.with(view.context)
            .load(url)
            .transform(CircleCrop())
            .into(view)
    }
}