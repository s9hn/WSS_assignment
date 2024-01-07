package com.example.wsselixir.data.mapper

import com.example.wsselixir.data.local.member.model.MemberEntity
import com.example.wsselixir.data.model.Follower

fun List<MemberEntity>.toData(): List<Follower> {
    return this.map {
        Follower(
            first_name = it.first_name,
            avatar = it.avatar
        )
    }
}