package com.github.zeroicq.plannerator.mvp.models

import android.icu.util.GregorianCalendar

class DayModel(val date: GregorianCalendar, val message: String) {
    val dayOfWeek  get() = date.get(GregorianCalendar.DAY_OF_WEEK)
}