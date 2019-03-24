package com.github.zeroicq.plannerator.util

import com.github.zeroicq.plannerator.R
import java.lang.IllegalArgumentException
import java.util.*

fun monthRes(month: Int) = when (month) {
    GregorianCalendar.MONDAY    -> R.string.monday
    GregorianCalendar.TUESDAY   -> R.string.tuesday
    GregorianCalendar.WEDNESDAY -> R.string.wednesday
    GregorianCalendar.THURSDAY  -> R.string.thursday
    GregorianCalendar.FRIDAY    -> R.string.friday
    GregorianCalendar.SATURDAY  -> R.string.saturday
    GregorianCalendar.SUNDAY    -> R.string.sunday
    else -> throw IllegalArgumentException("invalid resource")
}
