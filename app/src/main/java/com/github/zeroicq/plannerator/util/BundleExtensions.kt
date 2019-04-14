package com.github.zeroicq.plannerator.util

import android.icu.util.GregorianCalendar
import android.os.Bundle

fun Bundle.putGregorianCalendar(key: String, date: GregorianCalendar) {
    putLong(key, date.timeInMillis)
}

fun Bundle.getGregorianCalendar(key: String): GregorianCalendar {
    val dateInMillis = getLong(key)
    val date = (GregorianCalendar.getInstance() as GregorianCalendar)
    date.timeInMillis  = dateInMillis
    return date
}
