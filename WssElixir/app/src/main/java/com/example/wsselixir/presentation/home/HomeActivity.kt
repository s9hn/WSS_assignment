package com.example.wsselixir.presentation.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wsselixir.databinding.ActivityHomeBinding
import com.example.wsselixir.presentation.myinformation.MyInformationActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickRegisterBtn()
    }

    private fun clickRegisterBtn() {
        binding.btnHomeRegister.setOnClickListener {
            if (validateMyInformation()) {
                startActivity(Intent(this, MyInformationActivity::class.java))
            } else {
                Toast.makeText(this, ERROR_TOAST_MESSAGE, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateMyInformation(): Boolean {
        return binding.etHomeName.text.toString().isNotBlank()
    }

    companion object {
        const val ERROR_TOAST_MESSAGE = "이름을 입력하시오"
    }
}