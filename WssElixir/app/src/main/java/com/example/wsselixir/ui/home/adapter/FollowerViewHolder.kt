package com.example.wsselixir.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wsselixir.databinding.ItemFollowerBinding
import com.example.wsselixir.remote.UserResponseDto

class FollowerViewHolder(
    private val binding: ItemFollowerBinding,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onItemClick(position)
            }
        }
    }

    fun onBind(follower: UserResponseDto.User) {
        Glide.with(binding.root.context)
            .load(follower.avatar)
            .into(binding.ivFollowerProfile)
        binding.tvFollowerName.text = follower.first_name
    }
}