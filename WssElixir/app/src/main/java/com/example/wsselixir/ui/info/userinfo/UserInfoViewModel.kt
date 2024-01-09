package com.example.wsselixir.ui.info.userinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wsselixir.data.User

class UserInfoViewModel : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user get() = _user

    fun setUserInfo(user: User) {
        _user.value = user
    }
}