package com.example.wsselixir.data.repository.member

import android.util.Log
import com.example.wsselixir.data.local.member.InMemoryMembers
import com.example.wsselixir.data.mapper.toMemberEntity
import com.example.wsselixir.data.model.MemberEntity
import com.example.wsselixir.data.remote.api.ReqresApi

class MemberRepository(
    private val reqresApi: ReqresApi,
    private val inMemoryMembers: InMemoryMembers
) {

    suspend fun getUsers(): List<MemberEntity> {
        val remoteUsers = try {
            reqresApi.getUsers().users.map { it.toMemberEntity() }
        } catch (e: Exception) {
            Log.e("MemberRepository", "getUsers: ${e.message}")
            emptyList<MemberEntity>()
        }

        val localUsers = inMemoryMembers.memberEntities

        return remoteUsers + localUsers
    }

}
