package com.example.wsselixir.local

class InMemoryMembers {
    val memberEntities: List<MemberEntity>
        get() = listOf(
            MemberEntity("세훈", "https://reqres.in/img/faces/6-image.jpg"),
            MemberEntity("재원", "https://reqres.in/img/faces/5-image.jpg"),
            MemberEntity("명지", "https://reqres.in/img/faces/4-image.jpg"),
            MemberEntity("연진", "https://reqres.in/img/faces/3-image.jpg"),
            MemberEntity("준서", "https://reqres.in/img/faces/2-image.jpg"),
        )
}