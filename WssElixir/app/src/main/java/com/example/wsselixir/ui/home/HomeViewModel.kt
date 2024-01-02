package com.example.wsselixir.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wsselixir.remote.NetworkModule
import com.example.wsselixir.remote.UserResponseDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private var _homeUiState : MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Init)
    val homeUiState : StateFlow<HomeUiState> = _homeUiState
    private var _followers : MutableStateFlow<List<UserResponseDto.User>> = MutableStateFlow(emptyList())
    val followers : StateFlow<List<UserResponseDto.User>> = _followers

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
}