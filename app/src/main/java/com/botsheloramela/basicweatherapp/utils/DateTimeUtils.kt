package com.botsheloramela.basicweatherapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * Utility class for date and time operations.
 */
object DateTimeUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    private val dateFormat = DateTimeFormatter.ofPattern("d MMMM, EEEE")

    @RequiresApi(Build.VERSION_CODES.O)
    /**
     * Get the current date in the format "d MMMM, EEEE".
     */
    fun getCurrentDate(): String {
        val currentDate = LocalDate.now()
        return currentDate.format(dateFormat)
    }

    /**
     * Parse the date time text to a timestamp readable by the system.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun parseDtTxtToTimestamp(dtTxt: String): Long {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val localDateTime = LocalDateTime.parse(dtTxt, formatter)
        return localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond()
    }

    /**
     * Parse the date time text to a readable text in the format 3 AM etc
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun parseDtTxtToHour(dtTxt: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val localDateTime = LocalDateTime.parse(dtTxt, formatter)
        return localDateTime.format(DateTimeFormatter.ofPattern("h a"))
    }

    /**
     * Parse the date time text to a readable text in the format 6 September etc
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun parseDtTxtToDayMonth(dtTxt: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val localDateTime = LocalDateTime.parse(dtTxt, formatter)
        return localDateTime.format(DateTimeFormatter.ofPattern("d MMMM"))
    }

}