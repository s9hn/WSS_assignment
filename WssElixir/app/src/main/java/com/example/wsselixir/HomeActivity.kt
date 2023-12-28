package com.example.wsselixir

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wsselixir.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var mbtiSpinner: Spinner
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myMBTISpinner()
        infoRegistration()
    }
    private fun infoRegistration() {
        val btnRegistration = binding.btnHomeRegistration

        btnRegistration.setOnClickListener {
            val name = binding.etHomeName.text.toString()
            val mbti = binding.spinnerHomeMBTI.selectedItem.toString()

            if (name.isNotBlank()) {
                val intent = Intent(this@HomeActivity, MyInformationActivity::class.java).apply {
                    putExtra("name", name)
                    putExtra("mbti", mbti)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "이름을 입력하십시오.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun myMBTISpinner() {
        mbtiSpinner = binding.spinnerHomeMBTI

        val mbtiAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.typeMBTI,
            android.R.layout.simple_spinner_item
        )
        mbtiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mbtiSpinner.adapter = mbtiAdapter

    }
}