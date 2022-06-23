package com.example.wave.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location (
    val street: Street?,
    val city: String?,
    val state: String?,
    val country: String?,
    val postcode: String?,
    val coordinates: Coordinates?,
    val timezone: Timezone?
): Parcelable {
    fun getFullAddress(): String {
        return "${state ?: ""}, ${city ?: ""}, ${street?.name ?: ""} ${street?.number ?: ""}, ${postcode ?: ""}"
    }
}