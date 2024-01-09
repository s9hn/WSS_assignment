package com.example.wsselixir.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.wsselixir.R
import com.example.wsselixir.data.User
import com.example.wsselixir.databinding.ActivityMainBinding
import com.example.wsselixir.ui.info.InfoActivity
import com.example.wsselixir.ui.info.followerinfo.FollowerInfoViewModel
import com.example.wsselixir.util.context.toast


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var followerAdapter: FollowerAdapter

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private val followerInfoViewModel: FollowerInfoViewModel by lazy {
        ViewModelProvider(this)[FollowerInfoViewModel::class.java]
    }
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

        initFollowerAdapter()
        setupToast()
        setupMbtiSpinner()
        setupClickListeners()
        observeUserInfo()
    }

    private fun initFollowerAdapter() {
        followerAdapter = FollowerAdapter {
            val followerId = it.id
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
            performValidCheck()
        }
        binding.etMainInputName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                performValidCheck()
                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun performValidCheck() {
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
        val userId = addUserId()

        mainViewModel.enrollUserInfo(inputName, selectedMbti, userId)
    }

    private fun addUserId(): Int {
        val userId = sharedPreferences.getInt("USER_ID", 0) + 1
        with(sharedPreferences.edit()) {
            putInt("USER_ID", userId)
            apply()
        }
        return userId
    }

    private fun observeUserInfo() {
        mainViewModel.user.observe(this) {
            toast(getString(R.string.enrollUserInfo, it.id.toString()))
            isFollowerInfoFragment = false
            navigateToInfoActivity()
        }
    }

    private fun navigateToInfoActivity() {
        val intent = Intent(this, InfoActivity::class.java).apply {
            putExtra(
                "USER_INFO",
                User(
                    mainViewModel.user.value?.id ?: 0,
                    mainViewModel.user.value?.mbti ?: "",
                    mainViewModel.user.value?.name ?: ""
                )
            )
            putExtra("FOLLOWER_ID", followerInfoViewModel.followerId.value ?: 0)
            putExtra("PAGE_NUMBER", if (isFollowerInfoFragment) 1 else 0)
        }
        startActivity(intent)
    }
}