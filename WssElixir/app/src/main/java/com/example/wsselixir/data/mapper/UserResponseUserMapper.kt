package com.example.wsselixir.data.mapper

import com.example.wsselixir.data.model.Follower
import com.example.wsselixir.data.remote.response.UserResponseDto

fun UserResponseDto.User.toData(): Follower {
    return Follower(
        first_name = this.first_name,
        avatar = this.avatar
    )
}