package com.example.wsselixir.remote

import com.example.wsselixir.data.Follower
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(
    @SerialName("data")
    val data: User
) {
    @Serializable
    data class User(
        @SerialName("id")
        val id: Int,
        @SerialName("first_name")
        val firstName: String,
        @SerialName("avatar")
        val avatar: String
    ) {
        fun toFollower(): Follower {
            return Follower(
                id = id,
                firstName = firstName,
                avatar = avatar,
            )
        }
    }
}
