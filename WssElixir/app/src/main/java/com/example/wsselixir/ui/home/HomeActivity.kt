package com.example.wsselixir.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wsselixir.R
import com.example.wsselixir.data.dto.UsersResponseDto
import com.example.wsselixir.databinding.ActivityHomeBinding
import com.example.wsselixir.ui.detail.DetailActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        initDataBinding()
        initSpinner()
        observeFollowerData()
    }

    private fun initDataBinding() {
        with(binding) {
            homeViewModel = homeViewModel
            lifecycleOwner = this@HomeActivity
        }
    }

    private fun initSpinner() {
        val mbtiItems = resources.getStringArray(R.array.MBTI)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mbtiItems)
        binding.spinnerHomeMBTI.adapter = adapter
    }

    private fun observeFollowerData() {
        with(homeViewModel) {
            usersResponse.observe(this@HomeActivity) { usersResponse ->
                initRecyclerView(usersResponse)
            }
        }
    }

    private fun initRecyclerView(data: UsersResponseDto) {
        val rvFollower = binding.rvHomeFollower
        val rvFollowerAdapter = FollowerAdapter(data)
        rvFollower.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvFollower.adapter = rvFollowerAdapter

        rvFollowerAdapter.setItemClickListener(object : FollowerAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                navigateDetailActivity(position + 1)
            }
        })
    }

    private fun navigateDetailActivity(id: Int) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("id", id)
            putExtra("name", homeViewModel.myName.value)
            putExtra("mbti", homeViewModel.myMBTI.value)
        }
        startActivity(intent)
        finish()
    }

    /* 1차 엘릭서
    private fun showFollowerDialog(position: Int) {
        val bundle = Bundle().apply {
            putInt("position", position)
        }
        val dialog = FollowerDialog()
        dialog.arguments = bundle
        dialog.show(supportFragmentManager, "팔로워 dialog")
    }


    private fun clickRegisterBtn() {
        binding.btnHomeRegister.setOnClickListener {
            val userName = binding.etHomeName.text.toString()
            val userMBTI = binding.spinnerHomeMBTI.selectedItem.toString()

            when {
                validateUserName(userName) && validateUserMBTI(userMBTI) -> navigateMyInformation(
                    userName, userMBTI
                )

                validateUserName(userName) -> showToastShort(getString(R.string.toast_mbti))
                validateUserMBTI(userMBTI) -> showToastShort(getString(R.string.toast_name))
                else -> showToastShort(getString(R.string.toast_all))
            }
        }
    }

    private fun validateUserName(userName: String): Boolean {
        return userName.isNotBlank()
    }

    private fun validateUserMBTI(userMBTI: String): Boolean {
        return userMBTI != "선택안함"
    }

    private fun navigateMyInformation(userName: String, userMBTI: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.apply {
            putExtra("name", userName)
            putExtra("mbti", userMBTI)
        }
        startActivity(intent)
    } */
}