package com.example.wave.utils

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.util.*


class DateUtils {
    companion object {
        fun formatDate(dateTime: DateTime): String {
            val pattern = DateTimeFormat.patternForStyle("M-", Locale.getDefault())
            return DateTimeFormat.forPattern(pattern).print(dateTime)
        }
    }
}