package com.example.wsselixir.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wsselixir.data.dto.UsersResponseDto
import com.example.wsselixir.databinding.ItemFollowerBinding

class FollowerAdapter(private val usersData: UsersResponseDto) :
    RecyclerView.Adapter<FollowerViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            ItemFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding, itemClickListener)
    }

    override fun getItemCount(): Int = usersData.users.size

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(usersData.users[position])
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
}