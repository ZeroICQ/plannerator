package com.github.zeroicq.plannerator.repository

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import com.github.zeroicq.plannerator.mvp.models.DayModel
import com.github.zeroicq.plannerator.mvp.models.MonthModel
import com.github.zeroicq.plannerator.mvp.models.WeekModel
import com.github.zeroicq.plannerator.util.copyGregorian
import kotlin.collections.ArrayList

/**
 * @param date used for retrieving week
 */
class WeekTestRepository: WeekRepositoryInterface {

    override fun getWeekData(date: android.icu.util.GregorianCalendar): WeekModel {
        val iterWeek = date.copyGregorian().apply {
            val currDayOfWeek = get(GregorianCalendar.DAY_OF_WEEK)
            // make monday first day of week again
            if (currDayOfWeek == GregorianCalendar.SUNDAY)
                add(GregorianCalendar.WEEK_OF_YEAR, -1)
            set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.MONDAY)
        }

        val days = ArrayList<DayModel>()
        for (i in 1..7) {
            val dayDate = GregorianCalendar(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DATE))
            days.add(DayModel(dayDate, "tst $i"))
            iterWeek.add(GregorianCalendar.DAY_OF_YEAR, 1)
        }
        return WeekModel(days)
    }

}