package com.example.wsselixir.ui.info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.wsselixir.R
import com.example.wsselixir.databinding.ActivityInfoBinding
import com.example.wsselixir.ui.info.followerinfo.FollowerInfoFragment
import com.example.wsselixir.ui.info.userinfo.UserInfoFragment
import com.google.android.material.tabs.TabLayoutMediator

private const val NUM_PAGES = 2

class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupViewPager()
        setupTabLayout()
    }

    private fun setupBinding() {
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupViewPager() {
        viewPager = binding.vpInfo
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter
        val initialPage = intent.getIntExtra("PAGE_NUMBER", 0)
        viewPager.currentItem = initialPage
    }

    private fun setupTabLayout() {
        val tabLayout = binding.tlInfo
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.tiUserInfoTap)
                1 -> getString(R.string.tiFollowerInfoTap)
                else -> throw IllegalArgumentException("Invalid position: $position")
            }
        }.attach()
    }

    private inner class ScreenSlidePagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        private val pageFragmentCreators: Map<Int, () -> Fragment> =
            mapOf(0 to { UserInfoFragment() }, 1 to { FollowerInfoFragment() })

        private val pageFragmentInstances: MutableMap<Int, Fragment> = mutableMapOf()

        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            return pageFragmentInstances.getOrPut(position) {
                pageFragmentCreators[position]?.invoke()
                    ?: throw IllegalArgumentException("Invalid position: $position")
            }
        }
    }
}