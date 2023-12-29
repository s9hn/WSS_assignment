package com.example.wsselixir.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wsselixir.data.FollowerInformation
import com.example.wsselixir.databinding.ItemFollowerBinding

class FollowersAdapter(private var followers: List<FollowerInformation>) :
    RecyclerView.Adapter<FollowersAdapter.FollowerViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(follower: FollowerInformation.Followers)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    fun setFollowerList(followerList: List<FollowerInformation>) {
        followers = followerList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFollowerBinding.inflate(inflater, parent, false)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        val follower = followers[position]
        holder.bind(follower as FollowerInformation.Followers)
    }

    override fun getItemCount(): Int {
        return followers.size
    }

    inner class FollowerViewHolder(private val binding: ItemFollowerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val followerImage: ImageView = binding.imgFollower

        fun bind(follower: FollowerInformation.Followers) {
            Glide.with(itemView)
                .load(follower.followerImage)
                .into(followerImage)

            itemView.setOnClickListener {
                onItemClickListener.onItemClick(follower)
            }
        }

    }
}

