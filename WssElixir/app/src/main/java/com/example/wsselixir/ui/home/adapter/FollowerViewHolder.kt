package com.example.wsselixir.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wsselixir.data.model.MemberEntity
import com.example.wsselixir.databinding.ItemFollowerBinding

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

    fun onBind(follower: MemberEntity) {
        binding.follower = follower
    }

    companion object {
        fun create(parent: ViewGroup, onItemClick: (Int) -> Unit): FollowerViewHolder {
            val binding = ItemFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return FollowerViewHolder(binding, onItemClick)
        }
    }
}