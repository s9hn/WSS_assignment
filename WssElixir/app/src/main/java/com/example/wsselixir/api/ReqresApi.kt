package com.example.wsselixir.api

import com.example.wsselixir.data.dto.UserResponseDto
import com.example.wsselixir.data.dto.UsersResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ReqresApi {

    @GET("users")
    suspend fun getUsers(): UsersResponseDto

    @GET("users/{id}")
    suspend fun getUser(
        @Path("id") id: Int
    ): UserResponseDto
}
