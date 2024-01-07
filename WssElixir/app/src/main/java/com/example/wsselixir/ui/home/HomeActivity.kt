package com.example.wsselixir.ui.home

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wsselixir.App
import com.example.wsselixir.R
import com.example.wsselixir.databinding.ActivityHomeBinding
import com.example.wsselixir.ui.detail.DetailActivity
import com.example.wsselixir.ui.home.adapter.FollowerAdapter
import com.example.wsselixir.ui.model.LocalUser

class HomeActivity : AppCompatActivity() {
    private lateinit var homeBinding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModel.createFactory(application as App)
    }
    private val followerAdapter: FollowerAdapter by lazy {
        FollowerAdapter(::clickFollowerItem)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        initMBTISpinner()
        initRecyclerView()
        observeHomeUiState()
        observeValidationState()
    }

    private fun observeHomeUiState() {
        homeViewModel.homeUiState.observe(this) { state ->
            when (state) {
                is HomeUiState.Init -> {
                    homeViewModel.getUsers()
                }

                is HomeUiState.Success -> {
                    loadFollowerData()
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

    private fun clickFollowerItem(followerId: Int) {
        homeViewModel.setFollowerId(followerId)
        homeViewModel.validateInput(
            homeBinding.etHomeName.text.toString(),
            homeBinding.spinnerHomeMBTI.selectedItem.toString()
        )
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
                    navigateToMyInfoActivity()
                }
            }
        }

    }

    private fun navigateToMyInfoActivity() {
        val userName = homeBinding.etHomeName.text.toString()
        val userMBTI = homeBinding.spinnerHomeMBTI.selectedItem.toString()
        val intent = DetailActivity.createIntent(
            this,
            LocalUser(userName, userMBTI),
            homeViewModel.selectedFollowerId.value ?: 0
        )
        startActivity(intent)
    }

    private fun makeToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}