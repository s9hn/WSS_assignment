package com.example.wsselixir.presentation.main

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wsselixir.R
import com.example.wsselixir.data.followers
import com.example.wsselixir.databinding.ActivityMainBinding
import com.example.wsselixir.util.context.toast

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var followerAdapter: FollowerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFollowers()
        setupClickListeners()
    }

    private fun isInfoValid(): Boolean {
        val inputName = binding.etMainInputName.text.toString()
        return if (inputName.isEmpty()) {
            toast(getString(R.string.empty_name_message))
            false
        } else {
            true
        }
    }

    private fun setupClickListeners() {
        val inputName = binding.etMainInputName.text.toString()
        binding.btnMainName.setOnClickListener {
            if (isInfoValid()) {
                enrollUserInfo(inputName)
            }
        }
        binding.etMainInputName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (isInfoValid()) {
                    enrollUserInfo(inputName)
                }
                true
            } else {
                false
            }
        }
    }

    private fun enrollUserInfo(inputName: String) {
        toast("이름이 등록되었습니다")
    }

    private fun setupFollowers() {
        followerAdapter = FollowerAdapter()
        binding.rvFollower.adapter = followerAdapter
        binding.rvFollower.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        followerAdapter.submitList(followers)
    }
}