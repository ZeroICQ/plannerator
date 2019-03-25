package com.github.zeroicq.plannerator.mvp.presenters

import android.app.Application
import android.icu.util.GregorianCalendar
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.mvp.models.MonthModel
import com.github.zeroicq.plannerator.mvp.views.MonthView
import com.github.zeroicq.plannerator.repository.MonthTestRepository
import com.github.zeroicq.plannerator.util.copyGregorian
import com.github.zeroicq.plannerator.util.getMonthWithYearString
import javax.inject.Inject

@InjectViewState
class MonthPresenter: MvpPresenter<MonthView>() {
    // DI
    @Inject
    lateinit var app: Application

    @Inject
    lateinit var monthRepository : MonthTestRepository

    // properties
    lateinit var loadedMonths: ArrayList<MonthModel>

    private val LOAD_MONTHS = 10

    private val LOAD_THRESHOLD = 1
    private val LOAD_AMOUNT = 2

    var curMonthPos = LOAD_MONTHS / 2


    init {
        PlanneratorApplication.graph.inject(this)
        loadMonths()
    }

    private fun loadMonths() {
        val iterateMonth = (GregorianCalendar.getInstance() as GregorianCalendar).copyGregorian().apply {
            add(GregorianCalendar.MONTH, -curMonthPos)
        }

        loadedMonths = ArrayList()
        for (i in 1..LOAD_MONTHS) {
            loadedMonths.add(monthRepository.getMonthData(iterateMonth))
            iterateMonth.add(GregorianCalendar.MONTH, 1)
        }
        viewState.setToolBarText(getMonthWithYearString(app.applicationContext, loadedMonths[curMonthPos].date))
    }

    fun onClick() {
        viewState.test()
    }

    fun onMonthPosChange(pos: Int) {
        Log.d("Plannerator", "month snap pos changed to $pos")
        curMonthPos = pos+1
        viewState.setToolBarText(getMonthWithYearString(app.applicationContext, loadedMonths[curMonthPos].date))
        if (LOAD_MONTHS - 1 - curMonthPos == LOAD_THRESHOLD ) {
            for (i in 1..LOAD_AMOUNT) {
                loadedMonths.add(monthRepository.getMonthData(loadedMonths.last().date.copyGregorian().apply {
                    add(GregorianCalendar.MONTH, 1)
                }))
                loadedMonths.removeAt(0)
            }

            viewState.onRecylclerAdvance(LOAD_AMOUNT)

        } else if (curMonthPos == LOAD_THRESHOLD) {
            for (i in 1..LOAD_AMOUNT) {
                loadedMonths.add(0, monthRepository.getMonthData(loadedMonths.first().date.copyGregorian().apply {
                    add(GregorianCalendar.MONTH, -1)
                }))
                loadedMonths.removeAt(loadedMonths.lastIndex)
            }
            viewState.onRecylclerPrev(LOAD_AMOUNT)
        }

    }

}