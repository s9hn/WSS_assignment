package com.example.wsselixir.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.wsselixir.data.model.MemberEntity
import com.example.wsselixir.data.remote.response.UserResponseDto

class FollowerAdapter(private val onItemClick: (Int) -> Unit) :
    ListAdapter<MemberEntity, FollowerViewHolder>(FollowerDiffCallback) {

    companion object {
        private val FollowerDiffCallback = object :
            DiffUtil.ItemCallback<MemberEntity>() {
            override fun areItemsTheSame(
                oldItem: MemberEntity,
                newItem: MemberEntity
            ): Boolean {
                return oldItem.avatar == newItem.avatar
            }

            override fun areContentsTheSame(
                oldItem: MemberEntity,
                newItem: MemberEntity
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
