package com.example.wsselixir.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wsselixir.api.NetworkModule
import com.example.wsselixir.data.dto.UsersResponseDto
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _usersResponse: MutableLiveData<UsersResponseDto> = MutableLiveData()
    val usersResponse: LiveData<UsersResponseDto> = _usersResponse

    private val _myName: MutableLiveData<String> = MutableLiveData()
    val myName: MutableLiveData<String> = _myName

    private val _myMBTI: MutableLiveData<String> = MutableLiveData()
    val myMBTI: LiveData<String> = _myMBTI

    init {
        getFollowerData()
    }

    private fun getFollowerData() {
        viewModelScope.launch {
            kotlin.runCatching {
                NetworkModule.reqresApi.getUsers()
            }.onSuccess { response ->
                Log.d("tongsin", "success")
                _usersResponse.value = response
            }.onFailure {
                Log.d("tongsin", "fail")
            }
        }
    }

    fun updateMBTI(mbti: String) {
        _myMBTI.value = mbti
    }
}