package com.demo.githubtrend.feed.model

import com.demo.githubtrend.common.network.adapter.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

sealed class QueryCondition {
    abstract val query: String

    data class CreatedUntil(val days: Int) : QueryCondition() {
        override val query: String
            get() {
                val calendar = Calendar.getInstance()
                calendar.add(Calendar.DAY_OF_MONTH, -days)
                val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
                return "created:>${formatter.format(calendar.timeInMillis)}"
            }
    }
}
