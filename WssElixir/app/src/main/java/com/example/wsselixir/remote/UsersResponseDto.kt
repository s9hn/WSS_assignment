package com.example.wsselixir.remote

import com.example.wsselixir.data.Follower
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersResponseDto(
    @SerialName("page") val page: Int,
    @SerialName("per_page") val per_page: Int,
    @SerialName("total") val total: Int,
    @SerialName("total_pages") val total_pages: Int,
    @SerialName("data") val users: List<UserResponseDto.User>,
) {
    fun toFollowers(): List<Follower> {
        return users.map { it.toFollower() }
    }
}
