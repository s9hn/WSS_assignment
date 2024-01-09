package com.example.wsselixir.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.wsselixir.R

class ItemDiffCallback<T : Any>(
    val onItemsTheSame: (T, T) -> Boolean,
    val onContentsTheSame: (T, T) -> Boolean
) : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(
        oldItem: T, newItem: T
    ): Boolean = onItemsTheSame(oldItem, newItem)

    override fun areContentsTheSame(
        oldItem: T, newItem: T
    ): Boolean = onContentsTheSame(oldItem, newItem)
}

@BindingAdapter("bindCropProfileImage")
fun ImageView.bindProfileImage(imgUrl: String?) {
    Glide.with(context)
        .load(imgUrl)
        .error(
            Glide.with(context)
                .load(R.drawable.ic_default_profile)
                .circleCrop()
        )
        .circleCrop()
        .into(this)
}