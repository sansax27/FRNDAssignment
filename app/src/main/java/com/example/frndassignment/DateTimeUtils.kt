package com.example.frndassignment

import com.example.frndassignment.data.models.util.CalendarMonthInfoUtilDataModel
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.until

object DateTimeUtils {

    fun getCalendarMonthInfo(year: Int, month: Int): CalendarMonthInfoUtilDataModel {
        val monthFirstDayDate = LocalDate(year, month, 1)
        val nextMonthFirstDayDate = if (month<12) LocalDate(year, month+1, 1) else LocalDate(year+1, 1, 1)
        val previousMonthFirstDayDate = if (month==1) {
            LocalDate(year-1, 12, 1)
        } else {
            LocalDate(year, month-1, 1)
        }
        return CalendarMonthInfoUtilDataModel(
            convertMonOrdToSunOrd(monthFirstDayDate.dayOfWeek.ordinal),
            monthFirstDayDate.until(nextMonthFirstDayDate, DateTimeUnit.DAY), convertMonOrdToSunOrd(nextMonthFirstDayDate.dayOfWeek.ordinal)-1,
            previousMonthFirstDayDate.until(monthFirstDayDate, DateTimeUnit.DAY))
    }

    private fun convertMonOrdToSunOrd(ordinal: Int) = if (ordinal==6) 0 else ordinal+1



}