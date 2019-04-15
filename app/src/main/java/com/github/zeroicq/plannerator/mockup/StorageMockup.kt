package com.github.zeroicq.plannerator.mockup

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import com.github.zeroicq.plannerator.mvp.models.EventModel
import com.github.zeroicq.plannerator.util.copyGregorian
import com.mooveit.library.Fakeit
import com.thedeanda.lorem.LoremIpsum
import kotlin.collections.ArrayList

class StorageMockup {
    private val events = ArrayList<EventModel>()

    init {
        Fakeit.init()
        val lorem = LoremIpsum.getInstance()
        val today = GregorianCalendar.getInstance()

        for (i in 1..5) {
            val start = (today as GregorianCalendar).copyGregorian().apply {
                set(GregorianCalendar.HOUR_OF_DAY, i)
            }

            val end = start.copyGregorian().apply { add(GregorianCalendar.HOUR_OF_DAY, 1) }

            events.add(EventModel(start, end, lorem.getTitle(1, 4), Fakeit.rickAndMorty().quote()))
        }

        today.add(GregorianCalendar.DAY_OF_MONTH, -1)

        for (i in 1..3) {
            val start = (today as GregorianCalendar).copyGregorian().apply {
                set(GregorianCalendar.HOUR_OF_DAY, i+10)
            }

            val end = start.copyGregorian().apply { add(GregorianCalendar.HOUR_OF_DAY, 1) }

            for (j in 1..3) {
                events.add(EventModel(start, end, lorem.getTitle(1, 4), Fakeit.rickAndMorty().quote()))
            }

        }

    }

    fun addEvent(event: EventModel) {
        events.add(event)
    }

    fun getDayEvents(date: Calendar) : ArrayList<EventModel> {
        val result = ArrayList<EventModel>()

        for (e in events) {
            if (e.startDate.get(GregorianCalendar.YEAR) == date.get(GregorianCalendar.YEAR) &&
                e.startDate.get(GregorianCalendar.MONTH) == date.get(GregorianCalendar.MONTH) &&
                e.startDate.get(GregorianCalendar.DAY_OF_MONTH) == date.get(GregorianCalendar.DAY_OF_MONTH))
            {
                result.add(e)
            }
        }
        return result
    }
}