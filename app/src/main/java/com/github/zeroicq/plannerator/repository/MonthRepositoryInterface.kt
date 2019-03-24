package com.github.zeroicq.plannerator.repository

import android.icu.util.GregorianCalendar
import com.github.zeroicq.plannerator.mvp.models.MonthModel

interface MonthRepositoryInterface {
    fun getMonthData(date: GregorianCalendar): MonthModel
}