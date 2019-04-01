package com.github.zeroicq.plannerator.repository

import android.icu.util.GregorianCalendar
import com.github.zeroicq.plannerator.mvp.models.WeekModel

interface WeekRepositoryInterface {
    fun getWeekData(date: GregorianCalendar): WeekModel
}
