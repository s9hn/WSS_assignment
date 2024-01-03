package com.example.wsselixir.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.wsselixir.R
import com.example.wsselixir.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayout

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateMyInfo()
        updateFollowerId()
        observeFollowerData()
        selectTabListener()
    }

    private fun updateMyInfo() {
        val myName = intent.getStringExtra("name") ?: "0"
        val myMBTI = intent.getStringExtra("mbti") ?: "0"

        detailViewModel.updateMyInfo(myName, myMBTI)
    }

    private fun updateFollowerId() {
        val followerId = intent.getIntExtra("id", -1)
        Log.d("userDataFra", followerId.toString())
        if (followerId != -1) {
            detailViewModel.updateFollowerId(followerId)
            updateFollowerData()
        }
    }

    private fun updateFollowerData() {
        Log.d("userDataFra", "메소드 실행")
        with(detailViewModel) {
            userId.value?.let {
                updateFollowerInfo(it)
            }
        }
    }

    private fun observeFollowerData() {
        detailViewModel.userResponse.observe(this@DetailActivity) { response ->
            if (response != null) {
                setupFragment()
            }
        }
    }


    private fun setupFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.fcvDetail, FollowerInfoFragment())
            .commit()

        binding.tabDetail.getTabAt(1)?.select()
    }

    private fun selectTabListener() {
        binding.tabDetail.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val fragment = when (tab.position) {
                    0 -> MyInfoFragment()
                    1 -> FollowerInfoFragment()

                    else -> throw IllegalStateException("포지션이 없음")
                }
                supportFragmentManager.beginTransaction().replace(R.id.fcvDetail, fragment).commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }
}