package com.example.wsselixir.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wsselixir.data.dataclass.Follower
import com.example.wsselixir.data.mock.followerMockList
import com.example.wsselixir.databinding.ItemFollowerBinding

class FollowerAdapter(private val follower: List<Follower>) :
    RecyclerView.Adapter<FollowerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            ItemFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)
    }

    override fun getItemCount(): Int = followerMockList.size

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerMockList[position])
    }
}