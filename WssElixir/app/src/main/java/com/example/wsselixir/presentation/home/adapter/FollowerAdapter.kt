package com.example.wsselixir.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wsselixir.databinding.ItemFollowerBinding
import com.example.wsselixir.presentation.model.Follower

class FollowerAdapter(private val onItemClick: (Follower) -> Unit) :
    ListAdapter<Follower, FollowerAdapter.FollowerViewHolder>(FollowerDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val followerBinding =
            ItemFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(followerBinding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class FollowerViewHolder(private val binding: ItemFollowerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(follower: Follower) {
            binding.tvFollowerName.text = follower.name
            Glide.with(itemView.context)
                .load(follower.profileImage)
                .into(binding.ivFollowerProfile)

            binding.ivFollowerProfile.setOnClickListener {
                onItemClick(follower)
            }
        }
    }
}

class FollowerDiffUtil : DiffUtil.ItemCallback<Follower>() {
    override fun areItemsTheSame(oldItem: Follower, newItem: Follower): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Follower, newItem: Follower): Boolean {
        return oldItem == newItem
    }
}