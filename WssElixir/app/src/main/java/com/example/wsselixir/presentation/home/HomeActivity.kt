package com.example.wsselixir.presentation.home

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.wsselixir.R
import com.example.wsselixir.databinding.ActivityHomeBinding
import com.example.wsselixir.databinding.DialogHomeBinding
import com.example.wsselixir.presentation.home.adapter.FollowerAdapter
import com.example.wsselixir.presentation.model.Follower
import com.example.wsselixir.presentation.model.User
import com.example.wsselixir.presentation.myInfo.MyInfoActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var homeBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        initMBTISpinner()
        initPostButton()
        initRecyclerView()
    }

    private fun initMBTISpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.mbti_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            homeBinding.spinnerHomeMBTI.adapter = adapter
        }
    }

    private fun initPostButton() {
        homeBinding.btnHomePost.setOnClickListener {
            if (checkUserName() && checkUserMBTI()) {
                makeToast("이름과 MBTI를 입력해주세요")
            } else if (checkUserName()) {
                makeToast("이름을 입력해주세요")
            } else if (checkUserMBTI()) {
                makeToast("MBTI를 선택해주세요")
            } else {
                navigateToMyInfoActivity()
            }
        }
    }

    private fun checkUserName() = homeBinding.etHomeName.text.isNullOrBlank()
    private fun checkUserMBTI() =
        homeBinding.spinnerHomeMBTI.selectedItem.toString().isNullOrBlank()

    private fun navigateToMyInfoActivity() {
        val userName = homeBinding.etHomeName.text.toString()
        val userMBTI = homeBinding.spinnerHomeMBTI.selectedItem.toString()
        val intent = Intent(this, MyInfoActivity::class.java).apply {
            putExtra("userInfo", User(userName, userMBTI))
        }
        startActivity(intent)
    }

    private fun initRecyclerView() {
        val followerAdapter = FollowerAdapter(::showFollowerDialog).apply {
            submitList(dummyFollowers)
        }
        with(homeBinding.rvHomeFollower) {
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

    private fun makeToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
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