package com.example.wsselixir.ui.detail.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.wsselixir.databinding.FragmentUserBinding
import com.example.wsselixir.ui.detail.DetailViewModel

class UserFragment : Fragment() {
    private var _userBinding: FragmentUserBinding? = null
    private val userBinding: FragmentUserBinding
        get() = requireNotNull(_userBinding) { "binding is null" }
    private val detailViewModel: DetailViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _userBinding = FragmentUserBinding.inflate(inflater, container, false)
        return userBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userBinding.viewModel = detailViewModel
        userBinding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onDestroyView() {
        _userBinding = null
        super.onDestroyView()
    }

}