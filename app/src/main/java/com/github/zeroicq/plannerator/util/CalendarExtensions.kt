package com.github.zeroicq.plannerator.util

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar


fun GregorianCalendar.copyGregorian() : GregorianCalendar {
    return GregorianCalendar(
        this.get(GregorianCalendar.YEAR),
        this.get(GregorianCalendar.MONTH),
        this.get(GregorianCalendar.DAY_OF_MONTH),
        this.get(GregorianCalendar.HOUR_OF_DAY),
        this.get(GregorianCalendar.MINUTE),
        this.get(GregorianCalendar.SECOND)
    )
}

fun GregorianCalendar.isSameDay(other: GregorianCalendar) : Boolean {
    return get(GregorianCalendar.YEAR) == other.get(GregorianCalendar.YEAR) &&
           get(GregorianCalendar.MONTH) == other.get(GregorianCalendar.MONTH) &&
           get(GregorianCalendar.DAY_OF_MONTH) == other.get(GregorianCalendar.DAY_OF_MONTH)
}