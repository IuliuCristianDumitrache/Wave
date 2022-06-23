package com.example.wave.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Picture (
    val large: String?,
    val medium: String?,
    val thumbnail: String?
): Parcelable

