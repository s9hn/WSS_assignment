package com.example.wsselixir.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wsselixir.remote.NetworkModule
import com.example.wsselixir.remote.UserResponseDto
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private var _homeUiState: MutableLiveData<HomeUiState> =
        MutableLiveData(HomeUiState.Init)
    val homeUiState: LiveData<HomeUiState> = _homeUiState
    private var _followers: MutableLiveData<List<UserResponseDto.User>> =
        MutableLiveData(emptyList())
    val followers: LiveData<List<UserResponseDto.User>> = _followers
    private val _selectedFollower = MutableLiveData<UserResponseDto.User>()
    val selectedFollower: LiveData<UserResponseDto.User> = _selectedFollower
    private var _validationState: MutableLiveData<ValidationState> = MutableLiveData()
    val validationState: LiveData<ValidationState> = _validationState

    fun selectFollower(follower: UserResponseDto.User) {
        _selectedFollower.value = follower
    }

    fun getUsers() {
        viewModelScope.launch {
            runCatching {
                NetworkModule.reqresApi.getUsers()
            }.onSuccess {
                _followers.value = it.users
                _homeUiState.value = HomeUiState.Success
            }.onFailure {
                _homeUiState.value = HomeUiState.Error(it.message ?: "Error")
            }
        }
    }

    fun validateInput(name: String, MBTI: String) {
        if (checkUserName(name) && checkUserMBTI(MBTI)) {
            _validationState.value = ValidationState.NameANDMBTIIsBlank
            return
        }

        if (checkUserName(name)) {
            _validationState.value = ValidationState.NameIsBlank
            return
        }

        if (checkUserMBTI(MBTI)) {
            _validationState.value = ValidationState.MBTIIsBlank
            return
        }

        _validationState.value = ValidationState.Success
    }

    private fun checkUserName(name: String) = name.isNullOrBlank()
    private fun checkUserMBTI(MBTI: String) = MBTI.isNullOrBlank()
}