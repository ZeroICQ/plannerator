package com.github.zeroicq.plannerator.repository

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.mockup.StorageMockup
import com.github.zeroicq.plannerator.mvp.models.DayModel
import com.github.zeroicq.plannerator.mvp.models.MonthModel
import com.github.zeroicq.plannerator.mvp.models.WeekModel
import com.github.zeroicq.plannerator.util.copyGregorian
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * @param date used for retrieving week
 */
class WeekTestRepository: WeekRepositoryInterface {
    @Inject
    lateinit var storage : StorageMockup

    init {
        PlanneratorApplication.graph.inject(this)
    }

    override fun getWeekData(date: android.icu.util.GregorianCalendar): WeekModel {
        val iterWeek = date.copyGregorian().apply {
            val currDayOfWeek = get(GregorianCalendar.DAY_OF_WEEK)
            // make monday first day of week again
            //4todo: uncomment?
//            if (currDayOfWeek == GregorianCalendar.SUNDAY)
//                add(GregorianCalendar.WEEK_OF_YEAR, -1)
            set(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.MONDAY)
        }

        val days = ArrayList<DayModel>()
        for (i in 1..7) {
            val dayDate = GregorianCalendar(iterWeek.get(Calendar.YEAR), iterWeek.get(Calendar.MONTH), iterWeek.get(Calendar.DAY_OF_MONTH))
            days.add(DayModel(dayDate, storage.getDayEvents(dayDate)))
            iterWeek.add(GregorianCalendar.DAY_OF_YEAR, 1)
        }
        return WeekModel(days)
    }

}