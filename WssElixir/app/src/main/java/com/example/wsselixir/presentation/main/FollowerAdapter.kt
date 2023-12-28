package com.example.wsselixir.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wsselixir.R
import com.example.wsselixir.data.Follower
import com.example.wsselixir.databinding.ItemFollowerBinding
import com.example.wsselixir.util.view.ItemDiffCallback


class FollowerAdapter :
    ListAdapter<Follower, FollowerViewHolder>(ItemDiffCallback<Follower>(
        onItemsTheSame = { old, new -> old.id == new.id },
        onContentsTheSame = { old, new -> old == new }
    )) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFollowerBinding.inflate(inflater, parent, false)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}

class FollowerViewHolder(private val binding: ItemFollowerBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(follower: Follower) {
        binding.tvMainName.text = follower.name

        Glide.with(binding.root)
            .load(follower.profileImage)
            .error((R.drawable.ic_default_profile))
            .into(binding.ivFollowerProfile)
    }
}
