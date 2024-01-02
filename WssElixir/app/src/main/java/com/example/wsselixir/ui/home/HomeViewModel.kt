package com.example.wsselixir.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wsselixir.api.NetworkModule
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    init {
        getFollowerData()
    }

    fun getFollowerData() {
        viewModelScope.launch {
            kotlin.runCatching {
                NetworkModule.reqresApi.getUsers()
            }.onSuccess {
                Log.d("tongsin", "success")
            }.onFailure() {
                Log.d("tongsin", "fail")
            }
        }
    }
}