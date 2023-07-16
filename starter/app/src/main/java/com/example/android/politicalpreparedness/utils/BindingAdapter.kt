package com.example.android.politicalpreparedness.utils

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.politicalpreparedness.R
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

@BindingAdapter("isFollowText")
fun Button.bindFollowText(isFollow: Boolean) {
    text = if (isFollow) {
        context.getString(R.string.unfollow_button)
    } else {
        context.getString(R.string.follow_button)
    }
}

@BindingAdapter("isVisible")
fun View.bindContentVisibility(content: String?) {
    visibility = if (content.isNullOrEmpty()) {
        View.GONE
    } else {
        View.VISIBLE
    }
}
