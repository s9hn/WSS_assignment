package com.example.wsselixir.ui.myinformation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wsselixir.databinding.ActivityMyInformationBinding

class MyInformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initMyInformation()
    }

    private fun initMyInformation() {
        val name = intent.getStringExtra("name") ?: "0"
        val mbti = intent.getStringExtra("mbti") ?: "0"
        binding.tvMyInformationName.text = name
        binding.tvMyInformationMBTI.text = mbti
    }
}