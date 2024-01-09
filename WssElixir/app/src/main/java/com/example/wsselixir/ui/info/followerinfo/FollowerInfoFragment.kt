package com.example.wsselixir.ui.info.followerinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.wsselixir.databinding.FragmentFollowerInfoBinding

class FollowerInfoFragment : Fragment() {
    private var _binding: FragmentFollowerInfoBinding? = null
    private val binding get() = _binding!!

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

        binding.lifecycleOwner = viewLifecycleOwner
        binding.followerInfoViewModel = followerInfoViewModel

        observeFollowerId()
    }

    private fun observeFollowerId() {
        followerInfoViewModel.followerId.observe(viewLifecycleOwner) {
            followerInfoViewModel.updateFollowerInfo(it)
        }
    }
}