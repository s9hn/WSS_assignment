package com.example.wsselixir.data

data class Follower(
    val name: String,
    val profileImage: String
)

val followers = listOf(
    Follower("김명진", "https://avatars.githubusercontent.com/u/152427286?v=4"),
    Follower("김세훈", "https://avatars.githubusercontent.com/u/81347125?v=4"),
    Follower("백송현", "https://avatars.githubusercontent.com/u/153255948?v=4"),
    Follower("서재원", "https://avatars.githubusercontent.com/u/52442547?v=4"),
    Follower("손명지", "https://avatars.githubusercontent.com/u/114990782?v=4"),
    Follower("이연진", "https://avatars.githubusercontent.com/u/144861180?v=4"),
)