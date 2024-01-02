package com.example.wsselixir.ui.info.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.wsselixir.R
import com.example.wsselixir.data.User
import com.example.wsselixir.databinding.FragmentUserInfoBinding

class UserInfoFragment : Fragment() {
    private var _binding: FragmentUserInfoBinding? = null
    private val binding: FragmentUserInfoBinding
        get() = requireNotNull(_binding) { "바인딩 객체가 생성되지 않았다. 생성하고 불러라 임마!" }

    private lateinit var userInfoViewModel: UserInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userInfoViewModel = ViewModelProvider(this)[UserInfoViewModel::class.java]
        val user = getUserInfo()
        initUserInfo(user)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initUserInfo(user: User) {
        binding.tvUserName.text = getString(R.string.tvUserName, user.name)
        binding.tvUserMbti.text = getString(R.string.tvUserMbti, user.mbti)
    }

    private fun getUserInfo(): User {
        var user = User(0, "", "")
        userInfoViewModel.userInfo.observe(viewLifecycleOwner) { userInfo ->
            val userId = userInfo.getString("USER_ID")
            val userName = userInfo.getString("USER_NAME")
            val userMbti = userInfo.getString("USER_MBTI")
            user = User(userId!!.toInt(), userName!!, userMbti!!)
        }
        return user
    }
}