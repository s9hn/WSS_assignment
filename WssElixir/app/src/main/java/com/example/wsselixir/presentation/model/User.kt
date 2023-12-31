package com.example.wsselixir.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String,
    val MBTI: String? = ""
) : Parcelable
