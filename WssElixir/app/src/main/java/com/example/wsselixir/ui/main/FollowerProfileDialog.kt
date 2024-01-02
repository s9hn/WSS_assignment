package com.example.wsselixir.ui.main

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
        val followerDialog = createDialog()
        initDialogInfo(follower)
        setupExitButton(followerDialog)
        showFollowerDialog(followerDialog)
    }

    private fun createDialog(): Dialog {
        val followerDialog = Dialog(context)
        binding = DialogFollowerBinding.inflate(context.layoutInflater)
        followerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        followerDialog.setContentView(binding.root)
        followerDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        followerDialog.setCancelable(false)
        return followerDialog
    }

    private fun initDialogInfo(follower: Follower) {
        binding.tvDialogFollowerName.text = follower.firstName
        Glide.with(context)
            .load(follower.avatar)
            .error(
                Glide.with(context)
                    .load(R.drawable.ic_default_profile)
                    .circleCrop()
            )
            .circleCrop()
            .into(binding.ivDialogFollowerProfile)
    }

    private fun setupExitButton(followerDialog: Dialog) {
        binding.tvDialogFollowerExit.setOnClickListener {
            followerDialog.dismiss()
        }
    }

    private fun showFollowerDialog(followerDialog: Dialog) {
        followerDialog.show()
    }
}