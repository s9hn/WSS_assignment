package com.example.wsselixir.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.wsselixir.R
import com.example.wsselixir.data.FollowerInformation

class FollowersAdapter(private var followers: List<FollowerInformation>) :
    RecyclerView.Adapter<FollowersAdapter.FollowerViewHolder>() {

    fun setFollowerList(followerList: List<FollowerInformation>) {
        followers = followerList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_follower, parent, false)
        return FollowerViewHolder(view)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        val follower = followers[position]
        holder.bind(follower as FollowerInformation.Followers)
    }

    override fun getItemCount():Int {
        return followers.size
    }

    inner class FollowerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val followerImage: ImageView = itemView.findViewById(R.id.imgFollower)

        fun bind(follower: FollowerInformation.Followers) {
            Glide.with(itemView)
                .load(follower.followerImage)
                .into(followerImage)
        }
    }
}

