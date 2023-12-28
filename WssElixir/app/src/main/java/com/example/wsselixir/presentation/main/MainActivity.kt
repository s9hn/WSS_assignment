package com.example.wsselixir.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wsselixir.R
import com.example.wsselixir.data.followers
import com.example.wsselixir.databinding.ActivityMainBinding
import com.example.wsselixir.presentation.userinfo.UserActivity
import com.example.wsselixir.util.context.toast


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var followerAdapter: FollowerAdapter

    private val sharedPreferences by lazy {
        getSharedPreferences(
            "USER_NAME",
            Context.MODE_PRIVATE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        printFollowers()
        setupClickListeners()
    }

    private fun isInfoValid(inputName: String): Boolean {
        return if (inputName.isEmpty()) {
            toast(getString(R.string.emptyNameMessage))
            false
        } else {
            true
        }
    }

    private fun setupClickListeners() {
        binding.btnMainName.setOnClickListener { inputProcess() }
        binding.etMainInputName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                inputProcess()
                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun inputProcess() {
        val inputName = binding.etMainInputName.text.toString()
        if (isInfoValid(inputName)) {
            enrollUserInfo(inputName)
        }
    }

    private fun enrollUserInfo(inputName: String) {
        sharedPreferences.edit().putString(inputName, inputName).apply()
        toast("유저 정보가 저장되었습니다: $inputName")
        navigateToUserActivity(inputName)
    }

    private fun navigateToUserActivity(inputName: String) {
        val intent = Intent(this, UserActivity::class.java).apply {
            putExtra("user_name", inputName)
        }
        startActivity(intent)
    }

    private fun printFollowers() {
        followerAdapter = FollowerAdapter()
        binding.rvFollower.adapter = followerAdapter
        binding.rvFollower.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        followerAdapter.submitList(followers)
    }
}