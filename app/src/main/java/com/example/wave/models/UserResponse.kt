package com.example.wave.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse (
    val results: List<Results>?,
    val info: Info?,
) : Parcelable