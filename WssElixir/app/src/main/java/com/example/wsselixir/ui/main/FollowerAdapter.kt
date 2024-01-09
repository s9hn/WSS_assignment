package com.example.wsselixir.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wsselixir.data.Follower
import com.example.wsselixir.databinding.ItemFollowerBinding
import com.example.wsselixir.util.view.ItemDiffCallback
import com.example.wsselixir.util.view.bindProfileImage


class FollowerAdapter(private val itemClick: (Follower) -> Unit) :
    ListAdapter<Follower, FollowerViewHolder>(
        ItemDiffCallback<Follower>(onItemsTheSame = { old, new -> old.firstName == new.firstName },
            onContentsTheSame = { old, new -> old == new })
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFollowerBinding.inflate(inflater, parent, false)
        return FollowerViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(getItem(position), position)
    }
}

class FollowerViewHolder(
    private val binding: ItemFollowerBinding, private val itemClick: (Follower) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(follower: Follower, position: Int) {
        binding.tvFollowerName.text = follower.firstName
        binding.ivFollowerProfile.bindProfileImage(follower.avatar)

        itemView.setOnClickListener {
            if (position != RecyclerView.NO_POSITION) {
                itemClick.invoke(Follower(follower.id, follower.firstName, follower.avatar))
            }
        }
    }
}
