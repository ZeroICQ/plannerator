package com.github.zeroicq.plannerator.util

import android.content.Context
import android.icu.util.GregorianCalendar


fun getMonthWithYearString(ctxt: Context, date: GregorianCalendar): String {
    val month = date.get(GregorianCalendar.MONTH)
    val year  = date.get(GregorianCalendar.YEAR)
    return "${ctxt.getText(monthRes(month))}, $year"
}