package com.example.wsselixir.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wsselixir.R
import com.example.wsselixir.data.Follower
import com.example.wsselixir.data.User
import com.example.wsselixir.databinding.ActivityMainBinding
import com.example.wsselixir.ui.userinfo.UserActivity
import com.example.wsselixir.util.context.toast


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var followerAdapter: FollowerAdapter

    private val sharedPreferences by lazy {
        getSharedPreferences(
            "USER_ID",
            Context.MODE_PRIVATE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFollowers()
        setupMbtiSpinner()
        setupClickListeners()
    }

    private fun initFollowers() {
        val dialog = FollowerProfileDialog(this)

        followerAdapter = FollowerAdapter { follower ->
            dialog.showDialog(follower)
        }

        binding.rvFollower.adapter = followerAdapter
        binding.rvFollower.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        followerAdapter.submitList(followers)
    }

    private fun setupMbtiSpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.mbti_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spMainMbti.adapter = adapter
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
        val selectedMbti = getSelectedMbti()
        if (isNameValid(inputName) && isMbtiValid(selectedMbti)) {
            enrollUserInfo(inputName, selectedMbti)
        }
    }

    private fun getSelectedMbti(): String {
        val spinner: Spinner = findViewById(R.id.spMainMbti)
        return spinner.selectedItem.toString()
    }

    private fun isNameValid(inputName: String): Boolean {
        return if (inputName.isEmpty()) {
            toast(getString(R.string.emptyNameMessage))
            false
        } else {
            true
        }
    }

    private fun isMbtiValid(selectedMbti: String): Boolean {
        return if (selectedMbti.isEmpty()) {
            toast(getString(R.string.emptyMbtiMessage))
            false
        } else {
            true
        }
    }

    private fun enrollUserInfo(inputName: String, selectedMbti: String) {
        val userId = sharedPreferences.getInt("USER_ID", 0) + 1

        with(sharedPreferences.edit()) {
            putInt("USER_ID", userId)
            apply()
        }

        val user = User(userId, inputName, selectedMbti)

        toast(getString(R.string.enrollUserInfo, user.id.toString()))
        navigateToUserActivity(user)
    }

    private fun navigateToUserActivity(user: User) {
        val intent = Intent(this, UserActivity::class.java).apply {
            putExtra("USER", user)
        }
        startActivity(intent)
    }

    private val followers = listOf(
        Follower(1, "김명진", "https://avatars.githubusercontent.com/u/152427286?v=4"),
        Follower(2, "김세훈", "https://avatars.githubusercontent.com/u/81347125?v=4"),
        Follower(3, "백송현", "https://avatars.githubusercontent.com/u/153255948?v=4"),
        Follower(4, "서재원", "https://avatars.githubusercontent.com/u/52442547?v=4"),
        Follower(5, "손명지", "https://avatars.githubusercontent.com/u/114990782?v=4"),
        Follower(6, "이연진", "https://avatars.githubusercontent.com/u/144861180?v=4"),
    )
}