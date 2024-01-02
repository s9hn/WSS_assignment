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
import com.example.wsselixir.ui.userinfo.UserActivity
import com.example.wsselixir.util.context.toast


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var followerAdapter: FollowerAdapter
    private lateinit var viewModel: MainViewModel

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

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        initFollowerAdapter()
        setupMbtiSpinner()
        setupClickListeners()
    }

    private fun initFollowerAdapter() {
        val dialog = FollowerProfileDialog(this)

        followerAdapter = FollowerAdapter { follower ->
            dialog.showDialog(follower)
        }

        binding.rvFollower.adapter = followerAdapter
        viewModel.followers.observe(this) { followers ->
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
        binding.btnMainName.setOnClickListener { isInputValid() }
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
        val selectedMbti = viewModel.getSelectedMbti(binding.spMainMbti)
        if (viewModel.isInputValid(inputName, selectedMbti)) {
            val userId = sharedPreferences.getInt("USER_ID", 0) + 1
            viewModel.enrollUserInfo(inputName, selectedMbti, userId)
            viewModel.user.observe(this) { user ->
                toast(getString(R.string.enrollUserInfo, user.id.toString()))
                navigateToUserActivity(user)
            }
        }
    }

    private fun navigateToUserActivity(user: User) {
        val intent = Intent(this, UserActivity::class.java).apply {
            putExtra("USER", user)
        }
        startActivity(intent)
    }
}