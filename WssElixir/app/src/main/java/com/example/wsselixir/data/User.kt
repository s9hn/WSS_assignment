package com.example.wsselixir.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User(val id: Int, val name: String, val mbti: String) : Parcelable