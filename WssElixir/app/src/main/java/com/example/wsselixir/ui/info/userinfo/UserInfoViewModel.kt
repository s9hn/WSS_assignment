package com.example.wsselixir.ui.info.userinfo

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserInfoViewModel : ViewModel() {
    private val _userInfo = MutableLiveData<Bundle>()
    val userInfo: MutableLiveData<Bundle> get() = _userInfo

    fun setUserInfo(bundle: Bundle) {
        _userInfo.value = bundle
    }
}