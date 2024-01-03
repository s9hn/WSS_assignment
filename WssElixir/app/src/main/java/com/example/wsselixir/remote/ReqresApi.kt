package com.example.wsselixir.remote

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
