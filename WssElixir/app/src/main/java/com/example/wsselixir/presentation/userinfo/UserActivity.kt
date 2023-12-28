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
            "USER_NAME",
            Context.MODE_PRIVATE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userName = intent.getStringExtra("user_name")

        printUserInfo(userName ?: "")
    }

    private fun printUserInfo(userName: String) {
        val name = sharedPreferences.getString(userName, "")
        binding.tvUserName.text = getString(R.string.tvUserName, name ?: "")
    }
}