package com.botsheloramela.basicweatherapp.utils

import junit.framework.TestCase.assertEquals
import org.junit.Test

class DateTimeUtilsTest {
    @Test
    fun `getCurrentDate - returns formatted date`() {
        val expectedDate = "7 September, Saturday"
        val result = DateTimeUtils.getCurrentDate()
        assertEquals(expectedDate, result)
    }

    @Test
    fun `parseDtTxtToTimestamp - parses date time to timestamp`() {
        val dtTxt = "2024-09-07 12:00:00"
        val expectedTimestamp = 1725703200L
        val result = DateTimeUtils.parseDtTxtToTimestamp(dtTxt)
        assertEquals(expectedTimestamp, result)
    }

    @Test
    fun `parseDtTxtToHour - parses date time to hour`() {
        val dtTxt = "2024-09-07 12:00:00"
        val expectedHour = "12 PM"
        val result = DateTimeUtils.parseDtTxtToHour(dtTxt)
        assertEquals(expectedHour, result)
    }

    @Test
    fun `parseDtTxtToDayMonth - parses date time to day and month`() {
        val dtTxt = "2024-09-07 12:00:00"
        val expectedDayMonth = "7 September"
        val result = DateTimeUtils.parseDtTxtToDayMonth(dtTxt)
        assertEquals(expectedDayMonth, result)
    }
}