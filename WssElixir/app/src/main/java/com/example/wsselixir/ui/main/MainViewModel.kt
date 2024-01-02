package com.example.wsselixir.ui.main

import android.widget.Spinner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wsselixir.data.Follower
import com.example.wsselixir.data.User

class MainViewModel : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    private val _followers = MutableLiveData<List<Follower>>()
    val followers: LiveData<List<Follower>> get() = _followers

    init {
        loadFollowers()
    }

    private fun loadFollowers() {
        _followers.value = listOf(
            Follower(1, "김명진", "https://avatars.githubusercontent.com/u/152427286?v=4"),
            Follower(2, "김세훈", "https://avatars.githubusercontent.com/u/81347125?v=4"),
            Follower(3, "백송현", "https://avatars.githubusercontent.com/u/153255948?v=4"),
            Follower(4, "서재원", "https://avatars.githubusercontent.com/u/52442547?v=4"),
            Follower(5, "손명지", "https://avatars.githubusercontent.com/u/114990782?v=4"),
            Follower(6, "이연진", "https://avatars.githubusercontent.com/u/144861180?v=4"),
        )
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
