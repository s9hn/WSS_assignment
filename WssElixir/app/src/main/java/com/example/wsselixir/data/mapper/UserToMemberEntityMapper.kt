package com.example.wsselixir.data.mapper

import com.example.wsselixir.data.model.MemberEntity
import com.example.wsselixir.data.remote.response.UserResponseDto

fun UserResponseDto.User.toMemberEntity(): MemberEntity {
    return MemberEntity(
        first_name = this.first_name,
        avatar = this.avatar
    )
}