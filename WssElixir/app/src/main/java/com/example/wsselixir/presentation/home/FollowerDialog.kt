package com.example.wsselixir.presentation.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.wsselixir.data.mock.followerMockList
import com.example.wsselixir.databinding.DialogFollowerBinding

class FollowerDialog : DialogFragment() {
    private var _binding: DialogFollowerBinding? = null
    private val binding: DialogFollowerBinding
        get() = requireNotNull(_binding) { "초기화 안 됨" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFollowerBinding.inflate(inflater, container, false)
        initDialog()
        clickCloseBtn()
        return binding.root
    }

    private fun initDialog() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(false)
        val position = arguments?.getInt("position") ?: 0
        binding.ivHomeDialogPerson.setImageResource(followerMockList[position].img)
        binding.tvHomeDialogName.text = followerMockList[position].name
    }

    private fun clickCloseBtn() {
        binding.btnHomeDialogClose.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}