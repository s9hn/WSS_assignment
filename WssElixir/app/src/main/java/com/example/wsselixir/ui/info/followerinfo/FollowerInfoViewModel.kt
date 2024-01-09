package com.example.wsselixir.ui.info.followerinfo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wsselixir.data.Follower
import com.example.wsselixir.remote.NetworkModule
import kotlinx.coroutines.launch

class FollowerInfoViewModel : ViewModel() {
    private val _follower = MutableLiveData<Follower>()
    val follower: LiveData<Follower> get() = _follower

    private val _followerId = MutableLiveData(1)
    val followerId: LiveData<Int> get() = _followerId

    fun setFollowerId(id: Int) {
        _followerId.value = id
    }

    fun updateFollowerInfo(id: Int) {
        viewModelScope.launch {
            runCatching {
                NetworkModule.reqresApi.getUser(id)
            }.onSuccess {
                _follower.value = it.data.toFollower()
                Log.d("FollowerInfoViewModel", "Follower: $_follower")
            }.onFailure { exception ->
                Log.e("FollowerInfoViewModel", "Error getting follower: ", exception)
            }
        }
    }
}