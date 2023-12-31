package com.example.wsselixir.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.wsselixir.R
import com.example.wsselixir.data.Follower
import com.example.wsselixir.databinding.ItemFollowerBinding
import com.example.wsselixir.util.view.ItemDiffCallback


class FollowerAdapter(private val itemClick: (Follower) -> Unit) :
    ListAdapter<Follower, FollowerViewHolder>(ItemDiffCallback<Follower>(
        onItemsTheSame = { old, new -> old.id == new.id },
        onContentsTheSame = { old, new -> old == new }
    )) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFollowerBinding.inflate(inflater, parent, false)
        return FollowerViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}

class FollowerViewHolder(
    private val binding: ItemFollowerBinding,
    private val itemClick: (Follower) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            val follower = it.tag as? Follower
            follower?.let { itemClick.invoke(it) }
        }
    }

    fun onBind(follower: Follower) {
        binding.tvFollowerName.text = follower.name

        Glide.with(binding.root)
            .load(follower.profileImage)
            .error(
                Glide.with(binding.root)
                    .load(R.drawable.ic_default_profile)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            )
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .into(binding.ivFollowerProfile)

        itemView.tag = follower
    }
}
