package com.example.android.politicalpreparedness.network.jsonadapter

import java.text.SimpleDateFormat
import java.util.*

// Reference https://developer.android.com/reference/java/text/SimpleDateFormat
class DateConvert {
    private val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    fun fromJson(dateStr: String): Date? {
        return format.parse(dateStr)
    }

    fun toJson(date: Date?): String {
        return format.format(date!!)
    }
}
