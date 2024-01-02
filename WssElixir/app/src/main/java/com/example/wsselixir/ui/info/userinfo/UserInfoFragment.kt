package com.example.wsselixir.ui.info.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wsselixir.R
import com.example.wsselixir.data.User
import com.example.wsselixir.databinding.FragmentUserInfoBinding

class UserInfoFragment : Fragment() {
    private var _binding: FragmentUserInfoBinding? = null
    private val binding: FragmentUserInfoBinding
        get() = requireNotNull(_binding) { "바인딩 객체가 생성되지 않았다. 생성하고 불러라 임마!" }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUserInfo()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setupUserInfo() {
        val userId = requireActivity().intent.getIntExtra("USER_ID", 0)
        val userName = requireActivity().intent.getStringExtra("USER_NAME") ?: ""
        val userMbti = requireActivity().intent.getStringExtra("USER_MBTI") ?: ""

        val user = User(userId, userName, userMbti)
        updateUI(user)
    }

    private fun updateUI(user: User) {
        binding.tvUserName.text = getString(R.string.tvUserName, user.name)
        binding.tvUserMbti.text = getString(R.string.tvUserMbti, user.mbti)
    }
}