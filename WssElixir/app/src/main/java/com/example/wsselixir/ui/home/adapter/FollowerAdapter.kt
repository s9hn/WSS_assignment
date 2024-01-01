package com.example.wsselixir.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.wsselixir.databinding.ItemFollowerBinding
import com.example.wsselixir.ui.model.Follower

class FollowerAdapter(private val onItemClick: (Follower) -> Unit) :
    ListAdapter<Follower, FollowerViewHolder>(object :
        DiffUtil.ItemCallback<Follower>() {
        override fun areItemsTheSame(oldItem: Follower, newItem: Follower): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Follower, newItem: Follower): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding = ItemFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding) { position ->
            val item = getItem(position)
            onItemClick(item)
        }
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }
}
