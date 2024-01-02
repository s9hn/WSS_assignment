package com.example.wsselixir.ui.main

import android.util.Log
import android.widget.Spinner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wsselixir.data.Follower
import com.example.wsselixir.data.User
import com.example.wsselixir.remote.NetworkModule
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    private val _followers = MutableLiveData<List<Follower>>()
    val followers: LiveData<List<Follower>> get() = _followers

    init {
        viewModelScope.launch {
            runCatching {
                NetworkModule.reqresApi.getUsers()
            }.onSuccess {
                _followers.value = it.toFollowers()
                Log.d("MainActivity", "Followers: $_followers")
            }.onFailure { exception ->
                _followers.value = emptyList()
                Log.e("MainActivity", "Error getting followers: ", exception)
            }
        }
    }

    fun isInputValid(inputName: String, selectedMbti: String): Boolean {
        return isNameValid(inputName) && isMbtiValid(selectedMbti)
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
        val user = User(userId, inputName, selectedMbti)
        _user.value = user
    }
}
