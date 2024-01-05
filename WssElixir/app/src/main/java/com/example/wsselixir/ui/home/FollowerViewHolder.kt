package com.example.wsselixir.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.example.wsselixir.data.dto.UserResponseDto
import com.example.wsselixir.databinding.ItemFollowerBinding

class FollowerViewHolder(
    private val binding: ItemFollowerBinding,
    clickListener: (position: Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            clickListener(adapterPosition)
        }
    }

    fun onBind(users: UserResponseDto.User) {
        binding.users = users
    }
}