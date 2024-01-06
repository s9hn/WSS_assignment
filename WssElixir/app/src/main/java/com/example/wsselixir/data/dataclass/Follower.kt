package com.example.wsselixir.data.dataclass

import com.example.wsselixir.R

data class Follower(
    val id: Int,
    val name: String,
    val img: Int,
) {
    companion object {
        val followerMockList: MutableList<Follower> = mutableListOf(
            Follower(1, "김세훈", R.drawable.ksh),
            Follower(2, "서재원", R.drawable.sjw),
            Follower(3, "손명지", R.drawable.smj),
            Follower(4, "이연진", R.drawable.lyj),
            Follower(5, "최준서", R.drawable.cjs)
        )
    }
}