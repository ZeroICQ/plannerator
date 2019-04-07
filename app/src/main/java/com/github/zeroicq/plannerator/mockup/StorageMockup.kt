package com.github.zeroicq.plannerator.mockup

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import com.github.zeroicq.plannerator.mvp.models.EventModel
import com.github.zeroicq.plannerator.util.copyGregorian

class StorageMockup {
    private val events = ArrayList<EventModel>()

    init {
        val today = GregorianCalendar.getInstance()
        for (i in 1..3) {
            val d = (today as GregorianCalendar).copyGregorian().apply {
                set(GregorianCalendar.HOUR, i)
            }

            events.add(EventModel(d, "today at $i"))
        }

        today.add(GregorianCalendar.DAY_OF_MONTH, -1)

        for (i in 1..3) {
            val d = (today as GregorianCalendar).copyGregorian().apply {
                set(GregorianCalendar.HOUR, i+10)
            }

            events.add(EventModel(d, "$i first short"))
            events.add(EventModel(d, "$i second meeeeeeeedium"))
            events.add(EventModel(d, "$i third longlonglonglonglonglonglonglonglonglonglonglonglong"))
        }

    }

    fun addEvent(event: EventModel) {
        events.add(event)
    }

    fun getDayEvents(date: Calendar) : ArrayList<EventModel> {
        val result = ArrayList<EventModel>()

        for (e in events) {
            if (e.date.get(GregorianCalendar.YEAR) == date.get(GregorianCalendar.YEAR) &&
                e.date.get(GregorianCalendar.MONTH) == date.get(GregorianCalendar.MONTH) &&
                e.date.get(GregorianCalendar.DAY_OF_MONTH) == date.get(GregorianCalendar.DAY_OF_MONTH))
            {
                result.add(e)
            }
        }
        return result
    }
}