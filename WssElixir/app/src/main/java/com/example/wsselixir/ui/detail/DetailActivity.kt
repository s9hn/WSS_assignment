package com.example.wsselixir.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

        setupViewModel()
        observeFollowerData()
        selectTabListener()
    }

    private fun setupViewModel() {
        val myName = intent.getStringExtra("name") ?: "0"
        val myMBTI = intent.getStringExtra("mbti") ?: "0"
        val followerId = intent.getIntExtra("id", FOLLOWER_DEFAULT_VALUE)

        detailViewModel.updateMyInfo(myName, myMBTI)

        if (followerId != FOLLOWER_DEFAULT_VALUE) {
            detailViewModel.updateFollowerId(followerId)
            detailViewModel.updateFollowerInfo(followerId)
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
        replaceFragment(FollowerInfoFragment())
        binding.tabDetail.getTabAt(1)?.select()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fcvDetail, fragment).commit()
    }

    private fun selectTabListener() {
        binding.tabDetail.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val fragment = when (tab.position) {
                    0 -> MyInfoFragment()
                    1 -> FollowerInfoFragment()

                    else -> throw IllegalStateException("포지션이 없음")
                }
                replaceFragment(fragment)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }

    companion object {
        private const val FOLLOWER_DEFAULT_VALUE = -1
    }
}