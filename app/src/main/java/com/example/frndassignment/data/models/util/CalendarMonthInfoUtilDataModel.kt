package com.example.frndassignment.data.models.util

data class CalendarMonthInfoUtilDataModel(val firstDayWeekDayNumber: Int, val numberOfDaysInMonth: Int, val lastDayWeekDayNumber: Int,
    val previousMonthLastDate: Int,) {
    val totalCalendarVisibleDays = firstDayWeekDayNumber+numberOfDaysInMonth+6-lastDayWeekDayNumber


    val firstCalendarRow: List<Int> by lazy {
        val rowDays = mutableListOf<Int>()
        if (firstDayWeekDayNumber==0) {
            rowDays.addAll(1..7)
        } else {
            rowDays.addAll(previousMonthLastDate - (firstDayWeekDayNumber - 1)..previousMonthLastDate)
            rowDays.addAll(1..(7 - firstDayWeekDayNumber))
        }
        rowDays
    }

    val lastCalendarRow: List<Int> by lazy {
        val rowDays = mutableListOf<Int>()
        if (lastDayWeekDayNumber==6) {
            rowDays.addAll(numberOfDaysInMonth - lastDayWeekDayNumber..numberOfDaysInMonth)
        } else {
            rowDays.addAll(numberOfDaysInMonth - lastDayWeekDayNumber..numberOfDaysInMonth)
            rowDays.addAll(1..6 - lastDayWeekDayNumber)
        }
        rowDays
    }
}
