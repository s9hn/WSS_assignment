package com.example.wsselixir

import com.example.wsselixir.data.local.member.InMemoryMembers
import com.example.wsselixir.data.remote.api.ReqresApi
import com.example.wsselixir.data.repository.member.MemberRepository

class ServiceLocator {
    lateinit var memberRepository: MemberRepository
        private set

    fun provideMemberRepository(reqresApi: ReqresApi) {
        memberRepository = MemberRepository(reqresApi, InMemoryMembers())
    }
}