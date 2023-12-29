package com.example.wsselixir.data

import androidx.annotation.DrawableRes

sealed class FollowerInformation {
    data class Followers(
        @DrawableRes val followerImage: Int,
        val name: String
    ) : FollowerInformation()
}

