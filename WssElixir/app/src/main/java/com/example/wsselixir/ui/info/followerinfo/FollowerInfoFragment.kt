package com.example.wsselixir.ui.info.followerinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.wsselixir.R
import com.example.wsselixir.data.Follower
import com.example.wsselixir.databinding.FragmentFollowerInfoBinding

class FollowerInfoFragment : Fragment() {
    private var _binding: FragmentFollowerInfoBinding? = null
    private val binding: FragmentFollowerInfoBinding
        get() = requireNotNull(_binding) { "바인딩 객체가 생성되지 않았다. 생성하고 불러라 임마!" }

    private lateinit var followerInfoViewModel: FollowerInfoViewModel

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

        followerInfoViewModel = ViewModelProvider(this)[FollowerInfoViewModel::class.java]

        followerInfoViewModel.followerId.observe(viewLifecycleOwner) { it ->
            followerInfoViewModel.updateFollowerInfo(it)
            followerInfoViewModel.follower.observe(viewLifecycleOwner) {
                initFollowerInfo(it)
            }
        }

    }

    private fun initFollowerInfo(follower: Follower) {
        binding.tvFollowerName.text = follower.firstName
        Glide.with(this)
            .load(follower.avatar)
            .error(
                Glide.with(this)
                    .load(R.drawable.ic_default_profile)
                    .circleCrop()
            )
            .circleCrop()
            .into(binding.ivFollowerProfile)

    }
}