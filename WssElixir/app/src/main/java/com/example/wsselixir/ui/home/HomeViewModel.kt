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
}