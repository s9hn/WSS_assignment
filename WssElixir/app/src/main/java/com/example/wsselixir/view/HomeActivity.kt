package com.example.wsselixir.view

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wsselixir.R
import com.example.wsselixir.adapter.FollowersAdapter
import com.example.wsselixir.data.FollowerInformation
import com.example.wsselixir.databinding.ActivityHomeBinding
import com.example.wsselixir.databinding.DialogFollowerInfoBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var mbtiSpinner: Spinner
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myMBTISpinner()
        infoRegistration()
        initFollowerRecyclerView()
    }

    private fun infoRegistration() {
        val btnRegistration = binding.btnHomeRegistration

        btnRegistration.setOnClickListener {
            val name = binding.etHomeName.text.toString()
            val mbti = binding.spinnerHomeMBTI.selectedItem.toString()

            val isNameBlank = name.isBlank()
            val isMbtiNull = mbti.isEmpty()

            when {
                isNameBlank && isMbtiNull -> {
                    Toast.makeText(this, "정보를 모두 입력해주세요", Toast.LENGTH_SHORT).show()
                }

                isNameBlank -> {
                    Toast.makeText(this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show()
                }

                isMbtiNull -> {
                    Toast.makeText(this, "MBTI를 선택해주세요", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    val intent =
                        Intent(this@HomeActivity, MyInformationActivity::class.java).apply {
                            putExtra("name", name)
                            putExtra("mbti", mbti)
                        }
                    startActivity(intent)
                }
            }
        }
    }

    private fun myMBTISpinner() {
        mbtiSpinner = binding.spinnerHomeMBTI

        val mbtiAdapter = ArrayAdapter.createFromResource(
            this, R.array.typeMBTI, android.R.layout.simple_spinner_item
        )
        mbtiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mbtiSpinner.adapter = mbtiAdapter
    }

    private fun initFollowerRecyclerView() {
        val mockFriendList = listOf<FollowerInformation>(
            FollowerInformation.Followers(R.drawable.sehun, "세훈"),
            FollowerInformation.Followers(R.drawable.jawon, "재원"),
            FollowerInformation.Followers(R.drawable.junseo, "준서"),
            FollowerInformation.Followers(R.drawable.yeonjeen, "연진"),
            FollowerInformation.Followers(R.drawable.meongji, "명지"),
            FollowerInformation.Followers(R.drawable.songhyeon, "송현"),
            FollowerInformation.Followers(R.drawable.meongjin, "명진"),
        )
        val FollowerViewTypeAdapter = FollowersAdapter(mockFriendList)
        binding.layoutFollower.adapter = FollowerViewTypeAdapter
        FollowerViewTypeAdapter.setFollowerList(mockFriendList)

        FollowerViewTypeAdapter.setOnItemClickListener(object :
            FollowersAdapter.OnItemClickListener {
            override fun onItemClick(follower: FollowerInformation.Followers) {

                showFriendInfoDialog(follower)
            }
        })
    }

    private fun showFriendInfoDialog(follower: FollowerInformation.Followers) {
        val dialog = Dialog(this)
        val dialogBinding = DialogFollowerInfoBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        dialogBinding.imgDialogFollower.setImageResource(follower.followerImage)
        dialogBinding.tvDialogName.text = follower.name

        dialogBinding.btnDialogClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}