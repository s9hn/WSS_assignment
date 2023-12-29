package com.example.wsselixir.presentation.main

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.wsselixir.R
import com.example.wsselixir.data.Follower
import com.example.wsselixir.databinding.DialogFollowerBinding

class FollowerProfileDialog(private val context: AppCompatActivity) {
    private lateinit var binding: DialogFollowerBinding

    fun showDialog(follower: Follower) {
        val dlg = Dialog(context)
        binding = DialogFollowerBinding.inflate(context.layoutInflater)

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(binding.root)
        dlg.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dlg.setCancelable(false)

        binding.tvDialogFollowerName.text = follower.name
        Glide.with(context)
            .load(follower.profileImage)
            .error(
                Glide.with(context)
                    .load(R.drawable.ic_default_profile)
                    .circleCrop()
            )
            .circleCrop()
            .into(binding.ivDialogFollowerProfile)

        binding.tvDialogFollowerExit.setOnClickListener {
            dlg.dismiss()
        }

        dlg.show()
    }
}