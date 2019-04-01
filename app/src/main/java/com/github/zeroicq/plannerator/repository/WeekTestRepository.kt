package com.github.zeroicq.plannerator.repository

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import com.github.zeroicq.plannerator.mvp.models.DayModel
import com.github.zeroicq.plannerator.mvp.models.MonthModel
import com.github.zeroicq.plannerator.mvp.models.WeekModel
import kotlin.collections.ArrayList

/**
 * @param date used for retrieving month
 */
class WeekTestRepository(): WeekRepositoryInterface {

    override fun getWeekData(date: android.icu.util.GregorianCalendar): WeekModel {
        val firstDayInWeek = date.getActualMinimum(Calendar.DAY_OF_WEEK_IN_MONTH)
        val lastDayInWeek = date.getActualMaximum(Calendar.DAY_OF_WEEK_IN_MONTH)

        val days = ArrayList<DayModel>()
        for (i in firstDayInWeek..lastDayInWeek) {
            val dayDate = GregorianCalendar(date.get(Calendar.YEAR), date.get(Calendar.MONTH), i)

            days.add(DayModel(dayDate, "tst $i"))
        }
        return WeekModel(days)
    }

}