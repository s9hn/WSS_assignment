package com.example.wsselixir.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersResponseDto(
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val per_page: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val total_pages: Int,
    @SerialName("data")
    val users: List<UserResponseDto.User>,
)
