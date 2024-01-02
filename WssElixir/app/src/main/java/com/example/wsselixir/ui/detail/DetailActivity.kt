package com.example.wsselixir.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wsselixir.R
import com.example.wsselixir.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayout

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var followerId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFragment()
        selectTabListener()
    }

    private fun setupFragment() {
        followerId = intent.getIntExtra("id", 0)
        val fragment = FollowerInfoFragment().apply {
            arguments = Bundle().apply {
                putInt("id", followerId)
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.fcvDetail, fragment)
            .commit()

        binding.tabDetail.getTabAt(1)?.select()
    }

    private fun selectTabListener() {
        binding.tabDetail.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val fragment = when (tab.position) {
                    0 -> MyInfoFragment()
                    1 -> FollowerInfoFragment().apply {
                        arguments = Bundle().apply {
                            putInt("id", followerId)
                        }
                    }

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
//    추후에 수정 예정
//    private fun initMyInformation() {
//        binding.tvMyInformationName.text = name
//        binding.tvMyInformationMBTI.text = mbti
//    }