package com.example.wave.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dob (
    val date: String?,
    val age: Long?
): Parcelable
