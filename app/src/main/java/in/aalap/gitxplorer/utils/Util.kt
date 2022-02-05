package `in`.aalap.gitxplorer.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


object Util {
    @SuppressLint("SimpleDateFormat")
    fun getDates(date: String): String {
        if (date.isEmpty()) {
            return ""
        } else {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val outputFormat = SimpleDateFormat("dd-MM-yyyy")
            val date: Date? = inputFormat.parse("2018-04-10T04:00:00.000Z") ?: return ""
            return outputFormat.format(date!!)
        }
    }
}