package com.example.wsselixir.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wsselixir.databinding.ActivityDetailBinding
import com.example.wsselixir.ui.model.LocalUser

class DetailActivity : AppCompatActivity() {
    private lateinit var myInfoBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myInfoBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(myInfoBinding.root)

        initMyInfoUI()
    }

    private fun initMyInfoUI() {
        val localUserInfo: LocalUser = intent.getParcelableExtra("userInfo") ?: return
        myInfoBinding.tvMyInfoName.text = localUserInfo.name
        myInfoBinding.tvMyInfoMBTI.text = localUserInfo.MBTI
    }

    companion object {
        fun createIntent(
            context: Context,
            localUserInfo: LocalUser,
            followerId: Int
        ): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra("userInfo", localUserInfo)
                putExtra("followerId", followerId)
            }
        }
    }
}