package com.example.wsselixir.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wsselixir.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initMyInformation()
    }
//    추후에 수정 예정
//    private fun initMyInformation() {
//        val name = intent.getStringExtra("name") ?: "0"
//        val mbti = intent.getStringExtra("mbti") ?: "0"
//        binding.tvMyInformationName.text = name
//        binding.tvMyInformationMBTI.text = mbti
//    }
}