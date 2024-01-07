package com.example.wsselixir.data.repository.member

import android.util.Log
import com.example.wsselixir.data.local.member.InMemoryMembers
import com.example.wsselixir.data.mapper.toData
import com.example.wsselixir.data.model.Follower
import com.example.wsselixir.data.remote.api.ReqresApi

class MemberRepository(
    private val reqresApi: ReqresApi
) {

    suspend fun getUsers(): List<Follower> {
        val remoteUsers: List<Follower> = try {
            reqresApi.getUsers().users.map { it.toData() }
        } catch (e: Exception) {
            Log.e("MemberRepository", "getUsers: ${e.message}")
            emptyList<Follower>()
        }

        val localUsers = InMemoryMembers().memberEntities.toData()

        return remoteUsers + localUsers
    }

}
