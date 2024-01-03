package com.example.wsselixir.ui.detail.follower

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.wsselixir.databinding.FragmentFollowerBinding
import com.example.wsselixir.ui.detail.DetailViewModel

class FollowerFragment : Fragment() {
    private var _followerBinding: FragmentFollowerBinding? = null
    private val followerBinding: FragmentFollowerBinding
        get() = requireNotNull(_followerBinding) { "binding is null" }
    private val detailViewModel: DetailViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _followerBinding = FragmentFollowerBinding.inflate(inflater, container, false)
        return followerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followerBinding.viewModel = detailViewModel
        followerBinding.lifecycleOwner = viewLifecycleOwner

        detailViewModel.follower.observe(viewLifecycleOwner) { follower ->
            follower?.let {
                followerBinding.tvFollowerName.text = it.first_name
            }
        }
    }

    override fun onDestroyView() {
        _followerBinding = null
        super.onDestroyView()
    }

}