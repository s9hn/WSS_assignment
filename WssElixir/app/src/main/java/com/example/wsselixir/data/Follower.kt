package com.example.wsselixir.data

data class Follower(
    val id: Int,
    val name: String,
    val profileImage: String
)

val followers = listOf(
    Follower(id = 1, "김명진", "https://avatars.githubusercontent.com/u/152427286?v=4"),
    Follower(id = 2, "김세훈", "https://avatars.githubusercontent.com/u/81347125?v=4"),
    Follower(id = 3, "백송현", "https://avatars.githubusercontent.com/u/153255948?v=4"),
    Follower(id = 4, "서재원", "https://avatars.githubusercontent.com/u/52442547?v=4"),
    Follower(id = 5, "손명지", "https://avatars.githubusercontent.com/u/114990782?v=4"),
    Follower(id = 6, "이연진", "https://avatars.githubusercontent.com/u/144861180?v=4"),
)