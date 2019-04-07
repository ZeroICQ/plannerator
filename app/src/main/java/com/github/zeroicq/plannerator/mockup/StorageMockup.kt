package com.github.zeroicq.plannerator.mockup

import android.icu.util.Calendar
import com.github.zeroicq.plannerator.mvp.models.EventModel
import java.util.*

class StorageMockup {
    private val events = ArrayList<EventModel>()

    init {
        val today = Calendar.getInstance()
        for (i in 1..3)
            events.add(EventModel(today, "$i event"))
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