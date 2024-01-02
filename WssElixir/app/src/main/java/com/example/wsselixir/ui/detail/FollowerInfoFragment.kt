package com.example.wsselixir.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wsselixir.databinding.FragmentFollowerInfoBinding

class FollowerInfoFragment : Fragment() {
    private var _binding: FragmentFollowerInfoBinding? = null
    private val binding: FragmentFollowerInfoBinding
        get() = requireNotNull(_binding)

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

        val followerId = arguments?.getInt("id") ?: 0
        Log.d("followerId", followerId.toString())
    }
}