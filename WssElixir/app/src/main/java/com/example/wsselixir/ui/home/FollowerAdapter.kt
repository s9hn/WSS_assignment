package com.example.wsselixir.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wsselixir.data.dataclass.Follower
import com.example.wsselixir.databinding.ItemFollowerBinding

class FollowerAdapter :
    RecyclerView.Adapter<FollowerViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            ItemFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)
    }

    override fun getItemCount(): Int = Follower.followerMockList.size

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(Follower.followerMockList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
}