package com.example.wsselixir.ui.home

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.wsselixir.R
import com.example.wsselixir.databinding.ActivityHomeBinding
import com.example.wsselixir.databinding.DialogHomeBinding
import com.example.wsselixir.remote.UserResponseDto
import com.example.wsselixir.ui.DetailView.DetailViewActivity
import com.example.wsselixir.ui.home.adapter.FollowerAdapter
import com.example.wsselixir.ui.model.LocalUser
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeActivity : AppCompatActivity() {
    private lateinit var homeBinding: ActivityHomeBinding
    private lateinit var followerAdapter: FollowerAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        observeHomeState()
    }

    private fun observeHomeState() {
        homeViewModel.homeUiState.flowWithLifecycle(lifecycle)
            .onEach { state ->
                when (state) {
                    is HomeUiState.Init -> {
                        initMBTISpinner()
                        initAdapter()
                        initRecyclerView()
                        homeViewModel.getUsers()
                    }

                    is HomeUiState.Success -> {
                        followerAdapter.submitList(homeViewModel.followers.value)
                        makeToast("Followers를 불러왔습니다.")
                    }

                    is HomeUiState.Error -> {
                        Log.e("getUsers Error : ", state.message)
                    }
                }
            }.launchIn(lifecycleScope)
    }

    private fun initMBTISpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.mbti_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            homeBinding.spinnerHomeMBTI.adapter = adapter
        }
    }

    private fun validateInputs(): Boolean {
        if (checkUserName() && checkUserMBTI()) {
            makeToast("이름과 MBTI를 입력해주세요")
            return false
        }
        if (checkUserName()) {
            makeToast("이름을 입력해주세요")
            return false
        }
        if (checkUserMBTI()) {
            makeToast("MBTI를 선택해주세요")
            return false
        }
        return true
    }

    private fun checkUserName() = homeBinding.etHomeName.text.isNullOrBlank()
    private fun checkUserMBTI() =
        homeBinding.spinnerHomeMBTI.selectedItem.toString().isNullOrBlank()

    private fun navigateToMyInfoActivity() {
        val userName = homeBinding.etHomeName.text.toString()
        val userMBTI = homeBinding.spinnerHomeMBTI.selectedItem.toString()
        val intent = DetailViewActivity.createIntent(this, LocalUser(userName, userMBTI))
        startActivity(intent)
    }

    private fun initAdapter() {
        followerAdapter = FollowerAdapter(::showFollowerDialog)
        loadFollowerData()
    }

    private fun loadFollowerData() {
        followerAdapter.submitList(homeViewModel.followers.value)
    }

    private fun initRecyclerView() {
        with(homeBinding.rvHomeFollower) {
            adapter = followerAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun showFollowerDialog(follower: UserResponseDto.User) {

    }

    private fun makeToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}