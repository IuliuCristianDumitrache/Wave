package com.example.wave.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Street (
    val number: Long?,
    val name: String?
) : Parcelable