package com.github.zeroicq.plannerator.util

import com.github.zeroicq.plannerator.R
import java.lang.IllegalArgumentException
import java.util.*

fun weekdayRes(weekday: Int) = when (weekday) {
    GregorianCalendar.MONDAY    -> R.string.monday_abbr
    GregorianCalendar.TUESDAY   -> R.string.tuesday_abbr
    GregorianCalendar.WEDNESDAY -> R.string.wednesday_abbr
    GregorianCalendar.THURSDAY  -> R.string.thursday_abbr
    GregorianCalendar.FRIDAY    -> R.string.friday_abbr
    GregorianCalendar.SATURDAY  -> R.string.saturday_abbr
    GregorianCalendar.SUNDAY    -> R.string.sunday_abbr
    else -> throw IllegalArgumentException("invalid resource")
}

fun monthRes(month: Int) = when (month) {
    GregorianCalendar.JANUARY   -> R.string.january
    GregorianCalendar.FEBRUARY  -> R.string.february
    GregorianCalendar.MARCH     -> R.string.march
    GregorianCalendar.APRIL     -> R.string.april
    GregorianCalendar.MAY       -> R.string.may
    GregorianCalendar.JUNE      -> R.string.june
    GregorianCalendar.JULY      -> R.string.july
    GregorianCalendar.AUGUST    -> R.string.august
    GregorianCalendar.SEPTEMBER -> R.string.september
    GregorianCalendar.OCTOBER   -> R.string.october
    GregorianCalendar.NOVEMBER  -> R.string.november
    GregorianCalendar.DECEMBER  -> R.string.december
    else -> throw IllegalArgumentException("invalid resource")
}
