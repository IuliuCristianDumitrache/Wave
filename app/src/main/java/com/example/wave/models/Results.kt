package com.example.wave.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Results (
    val gender: String?,
    val name: Name?,
    val location: Location?,
    val email: String?,
    val login: Login?,
    val dob: Dob?,
    val registered: Dob?,
    val phone: String?,
    val cell: String?,
    val id: ID?,
    val picture: Picture?,
    val nat: String?
): Parcelable