package com.example.discountclub.core.common

import java.util.Calendar
import java.util.Date
import java.util.TimeZone

fun Date.toMidnight(): Date {
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
        time = this@toMidnight
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
    return calendar.time
}