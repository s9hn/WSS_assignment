package com.example.wsselixir.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
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
        selectSpinnerItem()
        observeFollowerData()
    }

    private fun initDataBinding() {
        binding.homeViewModel = this.homeViewModel
        binding.lifecycleOwner = this@HomeActivity
    }

    private fun initSpinner() {
        val mbtiItems = resources.getStringArray(R.array.MBTI)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mbtiItems)
        binding.spinnerHomeMBTI.adapter = adapter
    }

    private fun selectSpinnerItem() {
        binding.spinnerHomeMBTI.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>) {
                    // 선택되지 않은 경우
                }

                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    val selectedMBTI = parent.getItemAtPosition(position).toString()
                    homeViewModel.updateMBTI(selectedMBTI)
                }
            }
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
        val name = homeViewModel.myName.value ?: "SMJ"
        val mbti = homeViewModel.myMBTI.value ?: "기입 안 함"

        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("id", id)
            putExtra("name", name)
            putExtra("mbti", mbti)
        }
        startActivity(intent)
    }
}