package com.example.wsselixir.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.wsselixir.R
import com.example.wsselixir.databinding.ActivityDetailBinding
import com.example.wsselixir.ui.detail.follower.FollowerFragment
import com.example.wsselixir.ui.detail.user.UserFragment
import com.example.wsselixir.ui.model.LocalUser
import com.google.android.material.tabs.TabLayout

class DetailActivity : AppCompatActivity() {
    private lateinit var detailBinding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        getIntentData()
        setupInitFragment()
        setupTabLayout()
        observeDetailUiState()
    }

    private fun observeDetailUiState() {
        detailViewModel.detailUiState.observe(this) {
            when (it) {
                is DetailUiState.Init -> {
                    detailViewModel.updateFollowerInfo()
                }
                is DetailUiState.Success -> {
                    makeToast("Follower Update에 성공하였습니다.")
                }
                is DetailUiState.Error -> {
                    Log.e("updateFollower Error : ", it.message)
                }
            }
        }
    }

    private fun getIntentData() {
        val localUserInfo: LocalUser = intent.getParcelableExtra("userInfo") ?: return
        val followerId: Int = intent.getIntExtra("followerId",0)

        detailViewModel.setLocalUserInfo(localUserInfo)
        detailViewModel.setFollowerId(followerId)
    }

    private fun setupInitFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<FollowerFragment>(R.id.fcvDetail)
        }
        detailBinding.tbDetail.getTabAt(1)?.select()
    }

    private fun setupTabLayout() {
        val tabLayout = detailBinding.tbDetail
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> showFragment(UserFragment::class.java)
                    1 -> showFragment(FollowerFragment::class.java)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    private fun showFragment(fragmentClass: Class<out Fragment>) {
        supportFragmentManager.commit {
            replace(R.id.fcvDetail, fragmentClass, null)
            setReorderingAllowed(true)
        }
    }

    private fun makeToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun createIntent(
            context: Context,
            localUserInfo: LocalUser,
            followerId: Int
        ): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra("userInfo", localUserInfo)
                putExtra("followerId", followerId)
            }
        }
    }
}