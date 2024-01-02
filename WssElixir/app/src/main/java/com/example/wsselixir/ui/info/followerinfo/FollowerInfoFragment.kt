package com.example.wsselixir.ui.info.followerinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.wsselixir.data.Follower
import com.example.wsselixir.databinding.FragmentFollowerInfoBinding
import com.example.wsselixir.util.view.bindProfileImage

class FollowerInfoFragment : Fragment() {
    private var _binding: FragmentFollowerInfoBinding? = null
    private val binding: FragmentFollowerInfoBinding
        get() = requireNotNull(_binding) { "바인딩 객체가 생성되지 않았다. 생성하고 불러라 임마!" }

    private val followerInfoViewModel: FollowerInfoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowerInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val followerId = requireActivity().intent.getIntExtra("FOLLOWER_ID", 1)

        followerInfoViewModel.updateFollowerInfo(followerId)
        followerInfoViewModel.follower.observe(viewLifecycleOwner) {
            initFollowerInfo(it)
        }
    }

    private fun initFollowerInfo(follower: Follower) {
        binding.tvFollowerName.text = follower.firstName
        binding.ivFollowerProfile.bindProfileImage(follower.avatar)
    }
}