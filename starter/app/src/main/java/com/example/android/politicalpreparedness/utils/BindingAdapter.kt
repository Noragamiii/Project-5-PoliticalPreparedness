package com.example.android.politicalpreparedness.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("dateText")
fun TextView.bindDateText(date: Date?) {
    text = if (date == null) {
        ""
    } else {
        val format = SimpleDateFormat("EEEE, MMM. dd, yyyy • HH:mm z", Locale.US)
        format.format(date)
    }
}
