package com.example.wsselixir.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.wsselixir.data.remote.response.UserResponseDto

class FollowerAdapter(private val onItemClick: (Int) -> Unit) :
    ListAdapter<UserResponseDto.User, FollowerViewHolder>(FollowerDiffCallback) {

    companion object {
        private val FollowerDiffCallback = object :
            DiffUtil.ItemCallback<UserResponseDto.User>() {
            override fun areItemsTheSame(
                oldItem: UserResponseDto.User,
                newItem: UserResponseDto.User
            ): Boolean {
                return oldItem.avatar == newItem.avatar
            }

            override fun areContentsTheSame(
                oldItem: UserResponseDto.User,
                newItem: UserResponseDto.User
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        return FollowerViewHolder.create(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }

}
