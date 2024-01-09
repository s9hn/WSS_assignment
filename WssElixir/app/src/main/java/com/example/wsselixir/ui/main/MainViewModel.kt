package com.example.wsselixir.ui.main

import android.app.Application
import android.util.Log
import android.widget.Spinner
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.wsselixir.data.Follower
import com.example.wsselixir.data.User
import com.example.wsselixir.remote.NetworkModule
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _user = MutableLiveData<User>()
    var user: LiveData<User> = _user

    private val _followers = MutableLiveData<List<Follower>>()
    val followers: LiveData<List<Follower>> get() = _followers

    private var _enrollStatus: MutableLiveData<Int> = MutableLiveData(-1)
    val enrollStatus: LiveData<Int> get() = _enrollStatus

    init {
        viewModelScope.launch {
            runCatching {
                NetworkModule.reqresApi.getUsers()
            }.onSuccess {
                _followers.value = it.toFollowers()
                Log.d("MainViewModel", "Followers: $_followers")
            }.onFailure { exception ->
                _followers.value = emptyList()
                Log.e("MainViewModel", "Error getting followers: ", exception)
            }
        }
    }

    fun isInputValid(inputName: String, selectedMbti: String) {
        _enrollStatus.value = when {
            !isNameValid(inputName) && !isMbtiValid(selectedMbti) -> 0
            !isNameValid(inputName) -> 1
            !isMbtiValid(selectedMbti) -> 2
            else -> 3
        }
        _enrollStatus.value = -1
    }

    fun getSelectedMbti(spinner: Spinner): String {
        return spinner.selectedItem.toString()
    }

    private fun isNameValid(inputName: String): Boolean {
        return inputName.isNotEmpty()
    }

    private fun isMbtiValid(selectedMbti: String): Boolean {
        return selectedMbti.isNotEmpty()
    }

    fun enrollUserInfo(inputName: String, selectedMbti: String, userId: Int) {
        _user.value = User(userId, inputName, selectedMbti)
    }
}
