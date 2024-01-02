package com.example.wsselixir.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.wsselixir.R
import com.example.wsselixir.data.dto.UserResponseDto
import com.example.wsselixir.databinding.FragmentFollowerInfoBinding

class FollowerInfoFragment : Fragment() {
    private var _binding: FragmentFollowerInfoBinding? = null
    private val binding: FragmentFollowerInfoBinding
        get() = requireNotNull(_binding)

    private val detailViewModel: DetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_follower_info, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUserData()
    }

    private fun observeUserData() {
        detailViewModel.userResponse.observe(viewLifecycleOwner) { userResponse ->
            binding.follower = userResponse?.data
            userResponse?.data?.let { userData ->
                Glide.with(this)
                    .load(userData.avatar)
                    .circleCrop()
                    .into(binding.ivFollowerInfoImg)
            }
        }

        detailViewModel.userId.value?.let { id ->
            if (id != -1) {
                detailViewModel.updateFollowerInfo(id)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}