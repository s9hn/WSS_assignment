package com.example.wsselixir.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wsselixir.databinding.ActivityMyinformationBinding

class MyInformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyinformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyinformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setInformaionIntent()

    }

    private fun setInformaionIntent() {
        val intent = intent
        val name = intent.getStringExtra("name")
        val mbti = intent.getStringExtra("mbti")

        binding.tvMyInfoPutName.text = "$name"
        binding.tvMyInfoPutMBTI.text = "$mbti"

    }
}