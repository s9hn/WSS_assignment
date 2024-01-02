package com.example.wsselixir.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.wsselixir.databinding.ItemFollowerBinding
import com.example.wsselixir.remote.UserResponseDto

class FollowerAdapter(private val onItemClick: (Int) -> Unit) :
    ListAdapter<UserResponseDto.User, FollowerViewHolder>(object :
        DiffUtil.ItemCallback<UserResponseDto.User>() {
        override fun areItemsTheSame(oldItem: UserResponseDto.User, newItem: UserResponseDto.User): Boolean {
            return oldItem.avatar == newItem.avatar
        }

        override fun areContentsTheSame(oldItem: UserResponseDto.User, newItem: UserResponseDto.User): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding = ItemFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding) { position ->
            onItemClick(position+1)
        }
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }
}
