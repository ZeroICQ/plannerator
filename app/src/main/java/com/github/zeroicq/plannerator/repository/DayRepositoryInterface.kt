package com.github.zeroicq.plannerator.repository

import android.icu.util.GregorianCalendar
import com.github.zeroicq.plannerator.mvp.models.DayModel

interface DayRepositoryInterface {
    fun getDayData(date: GregorianCalendar): DayModel
}