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
    private val homeBinding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private lateinit var userName: String
    private lateinit var userMBTI: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(homeBinding.root)

        setRecyclerView()
    }

    private fun setPostButtonEvent() {
        homeBinding.btnHomePost.setOnClickListener {

        }
    }

    private fun setRecyclerView() {
        val followerAdapter = FollowerAdapter { follower ->
            showFollowerDialog(follower)
        }
        followerAdapter.submitList(dummyFollowers)

        homeBinding.rvHomeFollower.apply {
            adapter = followerAdapter
            layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun showFollowerDialog(follower: Follower) {
        val dialogHomeBinding = DialogHomeBinding.inflate(layoutInflater)
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.apply {
            setView(dialogHomeBinding.root)
            setCancelable(false)
        }

        dialogHomeBinding.itemFollower.tvFollowerName.text = follower.name
        Glide.with(this)
            .load(follower.profileImage)
            .into(dialogHomeBinding.itemFollower.ivFollowerProfile)

        val dialog = dialogBuilder.create()
        dialogHomeBinding.btnHomeDialogCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
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