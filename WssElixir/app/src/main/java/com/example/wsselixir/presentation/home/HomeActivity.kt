package com.example.wsselixir.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wsselixir.R
import com.example.wsselixir.databinding.ActivityHomeBinding
import com.example.wsselixir.presentation.myinformation.MyInformationActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private lateinit var userName: String
    private lateinit var userMBTI: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSpinner()
        initRecyclerView()
        clickRegisterBtn()
    }

    private fun initSpinner() {
        val mbtiItems = resources.getStringArray(R.array.MBTI)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mbtiItems)
        binding.spinnerHomeMBTI.adapter = adapter
    }

    private fun initRecyclerView() {
        val rvFollower = binding.rvHomeFollower
        val rvFollowerAdapter = FollowerAdapter()
        rvFollower.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvFollower.adapter = rvFollowerAdapter

        rvFollowerAdapter.setItemClickListener(object : FollowerAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                showFollowerDialog(position)
            }
        })
    }

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
            userName = binding.etHomeName.text.toString()
            userMBTI = binding.spinnerHomeMBTI.selectedItem.toString()
            when {
                validateUserName() && validateUserMBTI() -> navigateMyInformation()
                validateUserName() -> Toast.makeText(
                    this,
                    getString(R.string.toast_mbti), Toast.LENGTH_SHORT
                ).show()

                validateUserMBTI() -> Toast.makeText(
                    this,
                    getString(R.string.toast_name), Toast.LENGTH_SHORT
                ).show()

                else -> Toast.makeText(
                    this,
                    getString(R.string.toast_all),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun validateUserName(): Boolean {
        return userName.isNotBlank()
    }

    private fun validateUserMBTI(): Boolean {
        return userMBTI != "선택안함"
    }

    private fun navigateMyInformation() {
        val intent = Intent(this, MyInformationActivity::class.java)
        intent.apply {
            putExtra("name", userName)
            putExtra("mbti", userMBTI)
        }
        startActivity(intent)
    }
}