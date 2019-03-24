package com.github.zeroicq.plannerator.util

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar


fun GregorianCalendar.copyGregorian() : GregorianCalendar {
    return GregorianCalendar(
        this.get(GregorianCalendar.YEAR),
        this.get(GregorianCalendar.MONTH),
        this.get(GregorianCalendar.DATE)
    )
}