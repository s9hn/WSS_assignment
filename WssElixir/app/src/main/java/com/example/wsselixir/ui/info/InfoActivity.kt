package com.example.wsselixir.ui.info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wsselixir.R
import com.example.wsselixir.data.User
import com.example.wsselixir.databinding.ActivityInfoBinding
import com.example.wsselixir.ui.info.followerinfo.FollowerInfoFragment
import com.example.wsselixir.ui.info.followerinfo.FollowerInfoViewModel
import com.example.wsselixir.ui.info.userinfo.UserInfoFragment
import com.example.wsselixir.ui.info.userinfo.UserInfoViewModel
import com.google.android.material.tabs.TabLayoutMediator

class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding
    private lateinit var followerInfoViewModel: FollowerInfoViewModel
    private lateinit var userInfoViewModel: UserInfoViewModel
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        followerInfoViewModel = ViewModelProvider(this)[FollowerInfoViewModel::class.java]
        userInfoViewModel = ViewModelProvider(this)[UserInfoViewModel::class.java]

        setupFollowerInfo()
        setupViewPager()
        setupTabLayout()
        getUserInfoFromMain()
    }

    private fun setupFollowerInfo() {
        val followerId = intent.getIntExtra("FOLLOWER_ID", 1)
        followerInfoViewModel.setFollowerId(followerId)
    }

    private fun setupViewPager() {
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        binding.vpInfo.adapter = pagerAdapter
        val initialPage = intent.getIntExtra("PAGE_NUMBER", 0)
        binding.vpInfo.currentItem = initialPage
    }

    private fun setupTabLayout() {
        val tabLayout = binding.tlInfo
        TabLayoutMediator(tabLayout, binding.vpInfo) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.tiUserInfoTap)
                1 -> getString(R.string.tiFollowerInfoTap)
                else -> throw IllegalArgumentException("Invalid position: $position")
            }
        }.attach()
    }

    @Suppress("DEPRECATION")
    private fun getUserInfoFromMain() {
        user = intent.getParcelableExtra("USER_INFO")!!
        userInfoViewModel.setUserInfo(user)
    }
}

class ScreenSlidePagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> UserInfoFragment()
            1 -> FollowerInfoFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}
