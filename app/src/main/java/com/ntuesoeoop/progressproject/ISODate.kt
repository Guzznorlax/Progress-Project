package com.ntuesoeoop.progressproject

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

//transfer the date to String type
class ISODate {
    @RequiresApi(Build.VERSION_CODES.O)
    val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSZ", Locale.getDefault())

    val dateTimeFormatterSimple =
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ", Locale.getDefault())

    fun getFormattedCurrentTime(): String {
        var date = Date()
        return this.dateTimeFormatterSimple.format(date)
    }

}