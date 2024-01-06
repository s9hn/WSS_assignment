package com.example.wsselixir.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocalUser(
    val name: String,
    val MBTI: String? = ""
) : Parcelable
