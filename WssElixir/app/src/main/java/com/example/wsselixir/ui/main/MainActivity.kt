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
import com.example.wsselixir.ui.info.userinfo.UserInfoViewModel
import com.example.wsselixir.util.context.toast


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var followerAdapter: FollowerAdapter

    private lateinit var mainViewModel: MainViewModel
    private lateinit var userInfoViewModel: UserInfoViewModel
    private lateinit var followerInfoViewModel: FollowerInfoViewModel

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

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        userInfoViewModel = ViewModelProvider(this)[UserInfoViewModel::class.java]
        followerInfoViewModel = ViewModelProvider(this)[FollowerInfoViewModel::class.java]

        initFollowerAdapter()
        setupMbtiSpinner()
        setupClickListeners()
    }

    private fun initFollowerAdapter() {
        followerAdapter = FollowerAdapter { follower ->
            val followerId = follower.id
            followerInfoViewModel.setFollowerId(followerId)
            navigateInfoActivity()
        }

        binding.rvFollower.adapter = followerAdapter
        mainViewModel.followers.observe(this) { followers ->
            followerAdapter.submitList(followers)
        }
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
        binding.btnMainName.setOnClickListener { enrollUserInfo() }
        binding.etMainInputName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                enrollUserInfo()
                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun enrollUserInfo() {
        val inputName = binding.etMainInputName.text.toString()
        val selectedMbti = mainViewModel.getSelectedMbti(binding.spMainMbti)
        if (mainViewModel.isInputValid(inputName, selectedMbti)) {
            val userId = sharedPreferences.getInt("USER_ID", 0) + 1
            mainViewModel.enrollUserInfo(inputName, selectedMbti, userId)

            val userInfoBundle = Bundle().apply {
                putString("USER_NAME", inputName)
                putString("USER_MBTI", selectedMbti)
                putInt("USER_ID", userId)
            }
            userInfoViewModel.setUserInfo(userInfoBundle)

            mainViewModel.user.observe(this) { user ->
                toast(getString(R.string.enrollUserInfo, user.id.toString()))
                navigateInfoActivity()
            }
        }
    }

    private fun navigateInfoActivity() {
        val intent = Intent(this, InfoActivity::class.java)
        startActivity(intent)
    }
}