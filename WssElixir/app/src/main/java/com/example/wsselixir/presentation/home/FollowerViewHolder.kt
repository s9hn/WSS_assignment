package com.example.wsselixir.presentation.home

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wsselixir.data.dataclass.Follower
import com.example.wsselixir.databinding.ItemFollowerBinding

class FollowerViewHolder(private val binding: ItemFollowerBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(follower: Follower) {
        Glide.with(itemView.context)
            .load(follower.img)
            .into(binding.ivFollowerImg)
    }
}