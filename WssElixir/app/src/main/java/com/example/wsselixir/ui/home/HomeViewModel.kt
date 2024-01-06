package com.example.wsselixir.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wsselixir.data.model.MemberEntity
import com.example.wsselixir.data.repository.member.MemberRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val memberRepository: MemberRepository) : ViewModel() {
    private var _homeUiState: MutableLiveData<HomeUiState> = MutableLiveData(HomeUiState.Init)
    val homeUiState: LiveData<HomeUiState> get() = _homeUiState

    private var _validationState: MutableLiveData<ValidationState> = MutableLiveData()
    val validationState: LiveData<ValidationState> get() = _validationState

    private var _followers: MutableLiveData<List<MemberEntity>> =
        MutableLiveData(emptyList())
    val followers: LiveData<List<MemberEntity>> get() = _followers

    private val _selectedFollowerId = MutableLiveData<Int>()
    val selectedFollowerId: LiveData<Int> get() = _selectedFollowerId

    fun setFollowerId(followerId: Int) {
        _selectedFollowerId.value = followerId + 1
    }

    fun getUsers() {
        viewModelScope.launch {
            runCatching {
                memberRepository.getUsers()
            }.onSuccess {
                _followers.value = it
                _homeUiState.value = HomeUiState.Success
            }.onFailure {
                _homeUiState.value = HomeUiState.Error(it.message ?: "Error")
            }
        }
    }

    fun validateInput(name: String, MBTI: String) {
        _validationState.value = when {
            checkUserName(name) && checkUserMBTI(MBTI) -> ValidationState.NameANDMBTIIsBlank
            checkUserName(name) -> ValidationState.NameIsBlank
            checkUserMBTI(MBTI) -> ValidationState.MBTIIsBlank
            else -> ValidationState.Success
        }
    }


    private fun checkUserName(name: String) = name.isNullOrBlank()
    private fun checkUserMBTI(MBTI: String) = MBTI.isNullOrBlank()
}