package com.example.wsselixir.ui.userinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wsselixir.R
import com.example.wsselixir.data.User
import com.example.wsselixir.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user: User? = intent.getParcelableExtra("USER")

        printUserInfo(user ?: User(0, "", ""))
    }

    private fun printUserInfo(user: User) {
        binding.tvUserName.text = getString(R.string.tvUserName, user.name)
        binding.tvUserMbti.text = getString(R.string.tvUserMbti, user.mbti)
    }
}