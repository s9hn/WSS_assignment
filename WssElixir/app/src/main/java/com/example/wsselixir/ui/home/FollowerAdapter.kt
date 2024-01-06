package com.example.wsselixir.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wsselixir.data.dto.UserResponseDto
import com.example.wsselixir.databinding.ItemFollowerBinding

class FollowerAdapter(
    private val clickListener: (position: Int) -> Unit
) :
    RecyclerView.Adapter<FollowerViewHolder>() {
    private var usersData: List<UserResponseDto.User> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            ItemFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding, clickListener)
    }

    override fun getItemCount(): Int = usersData.size

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(usersData[position])
    }

    fun updateData(newData: List<UserResponseDto.User>) {
        usersData = newData
        notifyDataSetChanged()
    }
}