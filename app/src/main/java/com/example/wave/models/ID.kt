package com.example.wave.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ID (
    val name: String?,
    val value: String?,
): Parcelable
