package com.example.wsselixir.presentation.myInfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wsselixir.databinding.ActivityMyInfoBinding
import com.example.wsselixir.presentation.model.User

class MyInfoActivity : AppCompatActivity() {
    private lateinit var myInfoBinding: ActivityMyInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myInfoBinding = ActivityMyInfoBinding.inflate(layoutInflater)
        setContentView(myInfoBinding.root)

        initMyInfoUI()
    }

    private fun initMyInfoUI() {
        val userInfo: User = intent.getParcelableExtra("userInfo") ?: return
        myInfoBinding.tvMyInfoName.text = userInfo.name
        myInfoBinding.tvMyInfoMBTI.text = userInfo.MBTI
    }
}