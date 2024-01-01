package com.example.wsselixir.ui.DetailView

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wsselixir.databinding.ActivityDetailViewBinding
import com.example.wsselixir.ui.model.User

class DetailViewActivity : AppCompatActivity() {
    private lateinit var myInfoBinding: ActivityDetailViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myInfoBinding = ActivityDetailViewBinding.inflate(layoutInflater)
        setContentView(myInfoBinding.root)

        initMyInfoUI()
    }

    private fun initMyInfoUI() {
        val userInfo: User = intent.getParcelableExtra("userInfo") ?: return
        myInfoBinding.tvMyInfoName.text = userInfo.name
        myInfoBinding.tvMyInfoMBTI.text = userInfo.MBTI
    }

    companion object {
        fun createIntent(context: Context, userInfo: User): Intent {
            return Intent(context, DetailViewActivity::class.java).apply {
                putExtra("userInfo", userInfo)
            }
        }
    }
}