package com.example.wsselixir.view

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

            if (name.isNotBlank()) {
                val intent = Intent(this@HomeActivity, MyInformationActivity::class.java).apply {
                    putExtra("name", name)
                    putExtra("mbti", mbti)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, getString(R.string.homeFailRegistration), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun myMBTISpinner() {
        mbtiSpinner = binding.spinnerHomeMBTI

        val mbtiAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.typeMBTI,
            android.R.layout.simple_spinner_item
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
    }

}