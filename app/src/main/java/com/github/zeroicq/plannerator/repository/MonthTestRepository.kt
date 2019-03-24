package com.github.zeroicq.plannerator.repository

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import com.github.zeroicq.plannerator.mvp.models.DayModel
import com.github.zeroicq.plannerator.mvp.models.MonthModel
import kotlin.collections.ArrayList

/**
 * @param date used for retrieving month
 */
class MonthTestRepository(): MonthRepositoryInterface {

    override fun getMonthData(date: android.icu.util.GregorianCalendar): MonthModel {
        val firstDayInMonth = date.getActualMinimum(Calendar.DAY_OF_MONTH)
        val lastDayInMonth = date.getActualMaximum(Calendar.DAY_OF_MONTH)

        val days = ArrayList<DayModel>()

        for (i in firstDayInMonth..lastDayInMonth) {
            val dayDate = GregorianCalendar(date.get(Calendar.YEAR), date.get(Calendar.MONTH), i)

            days.add(DayModel(dayDate, "tst $i"))
        }
        return MonthModel(days)
    }

}