package com.example.wsselixir.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(
    @SerialName("data")
    val data: User
) {
    @Serializable
    data class User(
        @SerialName("first_name")
        val first_name: String,
        @SerialName("avatar")
        val avatar: String,
    )
}
용