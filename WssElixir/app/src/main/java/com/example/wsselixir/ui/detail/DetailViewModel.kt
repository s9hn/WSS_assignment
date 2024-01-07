package com.example.wsselixir.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wsselixir.data.mapper.toData
import com.example.wsselixir.data.model.Follower
import com.example.wsselixir.remote.NetworkModule
import com.example.wsselixir.ui.model.LocalUser
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private var _followerId = MutableLiveData<Int>()
    val followerId: LiveData<Int> get() = _followerId

    private var _localUserInfo = MutableLiveData<LocalUser>()
    val localUserInfo: LiveData<LocalUser> get() = _localUserInfo

    private var _detailUiState = MutableLiveData<DetailUiState>(DetailUiState.Init)
    val detailUiState: LiveData<DetailUiState> get() = _detailUiState

    private var _follower = MutableLiveData<Follower>()
    val follower: LiveData<Follower> get() = _follower

    fun setFollowerId(followerId: Int) {
        _followerId.value = followerId
    }

    fun setLocalUserInfo(localUserInfo: LocalUser) {
        _localUserInfo.value = localUserInfo
    }

    fun updateFollowerInfo() {
        viewModelScope.launch {
            runCatching {
                followerId.value?.let { NetworkModule.reqresApi.getUser(it) }
            }.onSuccess {
                if (it?.data != null) {
                    _follower.value = it.data.toData()
                    _detailUiState.value = DetailUiState.Success
                } else {
                    _detailUiState.value = DetailUiState.Error("Fetched data is null")
                }
            }.onFailure {
                _detailUiState.value = DetailUiState.Error(it.message ?: "error")
            }
        }
    }
}