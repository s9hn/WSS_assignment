package com.example.wsselixir.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wsselixir.R
import com.example.wsselixir.databinding.ActivityHomeBinding
import com.example.wsselixir.presentation.myinformation.MyInformationActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private lateinit var userName: String
    private lateinit var userMBTI: String

    private val rvFollowerAdapter = FollowerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initSpinner()
        initRecyclerView()
        clickRegisterBtn()
        clickFollowerImg()
    }

    private fun initSpinner() {
        val mbtiItems = resources.getStringArray(R.array.MBTI)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mbtiItems)
        binding.spinnerHomeMBTI.adapter = adapter
    }

    private fun initRecyclerView() {
        val rvFollower = binding.rvHomeFollower
        rvFollower.adapter = rvFollowerAdapter
    }

    private fun clickRegisterBtn() {
        binding.btnHomeRegister.setOnClickListener {
            userName = binding.etHomeName.text.toString()
            userMBTI = binding.spinnerHomeMBTI.selectedItem.toString()
            if (validateMyInformation()) {
                val intent = Intent(this, MyInformationActivity::class.java)
                intent.apply {
                    putExtra("name", userName)
                    putExtra("mbti", userMBTI)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, ERROR_TOAST_MESSAGE, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateMyInformation(): Boolean {
        return userName.isNotBlank() && ::userMBTI.isInitialized
    }

    private fun clickFollowerImg() {
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

    companion object {
        const val ERROR_TOAST_MESSAGE = "이름 및 MBTI를 입력하고 다시 시도하세요"
    }
}