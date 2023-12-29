package com.example.wsselixir.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
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

    private var isMbtiValid: Boolean = false

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

        printFollowers()
        setupMbtiSpinner()
        setupClickListeners()
    }

    private fun setupMbtiSpinner() {
        val spinner: Spinner = findViewById(R.id.spMainMbti)
        ArrayAdapter.createFromResource(
            this,
            R.array.mbti_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                isMbtiValid = true
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                isMbtiValid = false
            }
        }
    }

    private fun isNameValid(inputName: String): Boolean {
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
        val selectedMbti = getSelectedMbti()
        if (isNameValid(inputName) && isMbtiValid) {
            enrollUserInfo(inputName, selectedMbti)
        }
    }
    private fun getSelectedMbti(): String {
        val spinner: Spinner = findViewById(R.id.spMainMbti)
        return spinner.selectedItem.toString()
    }

    private fun enrollUserInfo(inputName: String, selectedMbti: String) {
        val userId = sharedPreferences.getInt("USER_ID", 0) + 1

        sharedPreferences.edit().apply {
            putInt("USER_ID", userId)
            putString("USER_NAME_${userId}", inputName)
            putString("USER_MBTI_${userId}", selectedMbti)
            apply()
        }

        toast(getString(R.string.enrollUserInfo, userId.toString()))
        navigateToUserActivity(userId)
    }

    private fun navigateToUserActivity(userId: Int) {
        val intent = Intent(this, UserActivity::class.java).apply {
            putExtra("USER_ID", userId)
        }
        startActivity(intent)
    }

    private fun printFollowers() {
        val dialog = FollowerProfileDialog(this)

        followerAdapter = FollowerAdapter { follower ->
            dialog.showDialog(follower)
        }

        binding.rvFollower.adapter = followerAdapter
        binding.rvFollower.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        followerAdapter.submitList(followers)
    }
}