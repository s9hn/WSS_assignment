package com.example.wsselixir

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.wsselixir.remote.NetworkModule
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
    companion object {
        val dummyFollowers = listOf<Follower>(
            Follower(
                "https://avatars.githubusercontent.com/u/26512925?v=4",
                "서재원"
            ),
            Follower(
                "https://avat가ars.githubusercontent.com/u/26512925?v=4",
                "김세훈"
            ),
            Follower(
                "https://avatars.githubusercontent.com/u/26512925?v=4",
                "손명지"
            ),
            Follower(
                "https://avatars.githubusercontent.com/u/26512925?v=4",
                "최준서"
            ),
            Follower(
                "https://avatars.githubusercontent.com/u/26512925?v=4",
                "이연진"
            ),
            Follower(
                "https://avatars.githubusercontent.com/u/26512925?v=4",
                "김명진"
            ),
            Follower(
                "https://avatars.githubusercontent.com/u/26512925?v=4",
                "백송현"
            ),
        )
    }
}