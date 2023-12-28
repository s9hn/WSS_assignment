package com.example.wsselixir.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.wsselixir.databinding.ActivityHomeBinding
import com.example.wsselixir.databinding.DialogHomeBinding
import com.example.wsselixir.presentation.home.adapter.FollowerAdapter

class HomeActivity : AppCompatActivity() {
    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val followerAdapter = FollowerAdapter(::showFollowerDialog).apply {
            submitList(dummyFollowers)
        }
        with(binding.rvHomeFollower) {
            adapter = followerAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun showFollowerDialog(follower: Follower) {
        DialogHomeBinding.inflate(layoutInflater).apply {
            setupFollowerDialog(this, follower)
        }
    }

    private fun setupFollowerDialog(binding: DialogHomeBinding, follower: Follower) {
        AlertDialog.Builder(this).apply {
            setView(binding.root)
            setCancelable(false)
            create().apply {
                binding.btnHomeDialogCancel.setOnClickListener { dismiss() }
                show()
            }
        }
        Glide.with(applicationContext)
            .load(follower.profileImage)
            .into(binding.itemFollower.ivFollowerProfile)
        binding.itemFollower.tvFollowerName.text = follower.name
    }

    companion object {
        private const val imageUrl =
            "https://github.com/DO-SOPT-ANDROID/jaewon-seo/assets/52442547/b8458fe5-8cda-462d-aa8e-c20b92e2579c"
        val dummyFollowers = listOf(
            Follower(
                0,
                imageUrl,
                "서재원"
            ),
            Follower(
                1,
                imageUrl,
                "김세훈"
            ),
            Follower(
                2,
                imageUrl,
                "손명지"
            ),
            Follower(
                3,
                imageUrl,
                "최준서"
            ),
            Follower(
                4,
                imageUrl,
                "이연진"
            ),
            Follower(
                5,
                imageUrl,
                "김명진"
            ),
            Follower(
                6,
                imageUrl,
                "백송현"
            ),
        )
    }
}