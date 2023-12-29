package com.example.wsselixir.presentation.myinformation

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
        binding.tvMyInformationName.text = intent.getStringExtra("name")
        binding.tvMyInformationMBTI.text = intent.getStringExtra("mbti")
    }
}