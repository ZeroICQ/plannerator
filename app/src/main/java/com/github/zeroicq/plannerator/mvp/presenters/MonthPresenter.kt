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

    var curMonthPos = LOAD_MONTHS / 2

//    private var displayMonth: GregorianCalendar


    init {
        PlanneratorApplication.graph.inject(this)
        //load months
//        displayMonth = GregorianCalendar.getInstance() as GregorianCalendar
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

    }

    fun onClick() {
        viewState.test()
    }

    fun onMonthPosChange(pos: Int) {
        Log.d("Plannerator", "month snap pos changed to $pos")
        curMonthPos = pos
        if (LOAD_MONTHS - curMonthPos == 2) {
            for (i in 1..2) {
                loadedMonths.add(monthRepository.getMonthData(loadedMonths.last().date.copyGregorian().apply {
                    add(GregorianCalendar.MONTH, 1)
                }))
                loadedMonths.removeAt(0)
            }

            viewState.scrollRecycler(curMonthPos)

        } else if (curMonthPos == 1) {
            //todo: load prev
        }
    }

}