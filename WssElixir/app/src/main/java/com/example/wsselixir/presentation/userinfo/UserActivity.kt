package com.example.wsselixir.presentation.userinfo

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wsselixir.R
import com.example.wsselixir.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding

    private val sharedPreferences by lazy {
        getSharedPreferences(
            "USER_ID",
            Context.MODE_PRIVATE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userId = intent.getIntExtra("USER_ID", 0)

        printUserInfo(userId)
    }

    private fun printUserInfo(userId: Int) {
        val name = sharedPreferences.getString("USER_NAME_${userId}", "")
        binding.tvUserName.text = getString(R.string.tvUserName, name ?: "")

        val mbti = sharedPreferences.getString("USER_MBTI_${userId}", "")
        binding.tvUserMbti.text = getString(R.string.tvUserMbti, mbti ?: "")
    }
}