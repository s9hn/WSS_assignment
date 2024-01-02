package com.example.wsselixir.ui.home

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wsselixir.R
import com.example.wsselixir.databinding.ActivityHomeBinding
import com.example.wsselixir.remote.UserResponseDto
import com.example.wsselixir.ui.DetailView.DetailViewActivity
import com.example.wsselixir.ui.home.adapter.FollowerAdapter
import com.example.wsselixir.ui.model.LocalUser

class HomeActivity : AppCompatActivity() {
    private lateinit var homeBinding: ActivityHomeBinding
    private lateinit var followerAdapter: FollowerAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        observeHomeUiState()
        observeValidationState()
    }

    private fun observeHomeUiState() {
        homeViewModel.homeUiState.observe(this) { state ->
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
        }
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

    private fun navigateToMyInfoActivity(followerName: String, followerAvatar: String) {
        val userName = homeBinding.etHomeName.text.toString()
        val userMBTI = homeBinding.spinnerHomeMBTI.selectedItem.toString()
        val intent = DetailViewActivity.createIntent(
            this,
            LocalUser(userName, userMBTI),
            UserResponseDto.User(followerName, followerAvatar)
        )
        startActivity(intent)
    }

    private fun initAdapter() {
        followerAdapter = FollowerAdapter(::clickFollowerItem)
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

    private fun clickFollowerItem(follower: UserResponseDto.User) {
        homeViewModel.selectFollower(follower)
        homeViewModel.validateInput(
            homeBinding.etHomeName.text.toString(),
            homeBinding.spinnerHomeMBTI.selectedItem.toString()
        )
    }

    private fun observeValidationState() {
        homeViewModel.validationState.observe(this) { state ->
            when (state) {
                ValidationState.NameANDMBTIIsBlank -> {
                    makeToast("이름과 MBTI를 입력해주세요")
                }

                ValidationState.NameIsBlank -> {
                    makeToast("이름을 입력해주세요")
                }

                ValidationState.MBTIIsBlank -> {
                    makeToast("MBTI를 선택해주세요")
                }

                ValidationState.Success -> {
                    homeViewModel.selectedFollower.value?.let { follower ->
                        navigateToMyInfoActivity(follower.first_name, follower.avatar)
                    }
                }
            }
        }

    }

    private fun makeToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}