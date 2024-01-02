package com.example.wsselixir.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.wsselixir.R
import com.example.wsselixir.databinding.ActivityMainBinding
import com.example.wsselixir.ui.info.InfoActivity
import com.example.wsselixir.ui.info.followerinfo.FollowerInfoViewModel
import com.example.wsselixir.util.context.toast


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var followerAdapter: FollowerAdapter

    private lateinit var mainViewModel: MainViewModel
    private lateinit var followerInfoViewModel: FollowerInfoViewModel
    private var isFollowerInfoFragment = false

    private val sharedPreferences by lazy {
        getSharedPreferences(
            "USER_ID", Context.MODE_PRIVATE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        followerInfoViewModel = ViewModelProvider(this)[FollowerInfoViewModel::class.java]

        initFollowerAdapter()
        setupToast()
        setupMbtiSpinner()
        setupClickListeners()
    }

    private fun initFollowerAdapter() {
        followerAdapter = FollowerAdapter { follower ->
            val followerId = follower.id
            followerInfoViewModel.setFollowerId(followerId)
            when (mainViewModel.user.value) {
                null -> toast(getString(R.string.userEmptyMessage))
                else -> followerInfoViewModel.followerId.observe(this) {
                    isFollowerInfoFragment = true
                    navigateToInfoActivity()
                }
            }
        }

        binding.rvFollower.adapter = followerAdapter
        mainViewModel.followers.observe(this) { followers ->
            followerAdapter.submitList(followers)
        }
    }

    private fun setupMbtiSpinner() {
        ArrayAdapter.createFromResource(
            this, R.array.mbti_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spMainMbti.adapter = adapter
        }
    }

    private fun setupClickListeners() {
        binding.btnMainName.setOnClickListener {
            isInputValid()
        }
        binding.etMainInputName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                isInputValid()
                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun isInputValid() {
        val inputName = binding.etMainInputName.text.toString()
        val selectedMbti = mainViewModel.getSelectedMbti(binding.spMainMbti)
        mainViewModel.isInputValid(inputName, selectedMbti)
    }

    private fun setupToast() {
        mainViewModel.enrollStatus.observe(this) {
            when (it) {
                0 -> {
                    toast(getString(R.string.emptyAllMessage))
                }

                1 -> {
                    toast(getString(R.string.emptyNameMessage))
                }

                2 -> {
                    toast(getString(R.string.emptyMbtiMessage))
                }

                3 -> {
                    enrollUserInfo()
                }
            }
        }
    }

    private fun enrollUserInfo() {
        val inputName = binding.etMainInputName.text.toString()
        val selectedMbti = mainViewModel.getSelectedMbti(binding.spMainMbti)
        val userId = sharedPreferences.getInt("USER_ID", 0) + 1
        mainViewModel.enrollUserInfo(inputName, selectedMbti, userId)
        mainViewModel.user.observe(this) {
            toast(getString(R.string.enrollUserInfo, userId.toString()))
            isFollowerInfoFragment = false
            navigateToInfoActivity()
        }
    }

    private fun navigateToInfoActivity() {
        val intent = Intent(this, InfoActivity::class.java).apply {
            putExtra("USER_ID", mainViewModel.user.value?.id ?: 0)
            putExtra("USER_NAME", mainViewModel.user.value?.name ?: "")
            putExtra("USER_MBTI", mainViewModel.user.value?.mbti ?: "")
            putExtra("FOLLOWER_ID", followerInfoViewModel.followerId.value ?: 0)
            when (isFollowerInfoFragment) {
                true -> putExtra("PAGE_NUMBER", 1)
                false -> putExtra("PAGE_NUMBER", 0)
            }
        }
        startActivity(intent)
    }
}