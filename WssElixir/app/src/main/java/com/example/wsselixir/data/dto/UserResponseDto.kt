package com.example.wsselixir.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(
    @SerialName("data")
    var data: User
) {
    @Serializable
    data class User(
        @SerialName("first_name")
        val first_name: String,
        @SerialName("avatar")
        val avatar: String
    )
}
