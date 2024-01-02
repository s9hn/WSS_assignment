package com.example.wsselixir.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wsselixir.data.dto.UserResponseDto
import com.example.wsselixir.databinding.ItemFollowerBinding

class FollowerViewHolder(
    private val binding: ItemFollowerBinding,
    private val itemClickListener: FollowerAdapter.OnItemClickListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(users: UserResponseDto.User) {
        Glide.with(itemView.context)
            .load(users.avatar).circleCrop()
            .into(binding.ivFollowerImg)

        itemView.setOnClickListener {
            itemClickListener.onClick(it, adapterPosition)
        }
    }
}