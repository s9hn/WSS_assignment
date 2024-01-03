package com.example.wsselixir.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wsselixir.api.NetworkModule
import com.example.wsselixir.data.dto.UserResponseDto
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val _followerResponse: MutableLiveData<UserResponseDto.User> = MutableLiveData()
    val userResponse: LiveData<UserResponseDto.User> = _followerResponse

    private val _followerId: MutableLiveData<Int> = MutableLiveData()
    val followerId: LiveData<Int> = _followerId

    private val _myName: MutableLiveData<String> = MutableLiveData()
    val myName: LiveData<String> = _myName

    private val _myMBTI: MutableLiveData<String> = MutableLiveData()
    val myMBTI: LiveData<String> = _myMBTI

    fun updateFollowerId(updateId: Int) {
        _followerId.value = updateId
    }

    fun updateFollowerInfo(id: Int) {
        viewModelScope.launch {
            runCatching {
                NetworkModule.reqresApi.getUser(id)
            }.onSuccess { result ->
                _followerResponse.value = result.data
                Log.d("tongsinDetail", result.data.toString())
            }
                .onFailure { throwable ->
                    Log.e("tongsinDetail", throwable.toString())
                }
        }
    }

    fun updateMyInfo(name: String, mbti: String) {
        _myName.value = name
        _myMBTI.value = mbti
    }
}