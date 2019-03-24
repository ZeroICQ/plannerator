package com.github.zeroicq.plannerator.mvp.presenters

import android.app.Application
import android.icu.util.GregorianCalendar
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

    val curMonthPos = LOAD_MONTHS / 2

//    private var displayMonth: GregorianCalendar


    init {
        PlanneratorApplication.graph.inject(this)
        //load months
//        displayMonth = GregorianCalendar.getInstance() as GregorianCalendar
        loadMonths()
    }

    private fun loadMonths() {
        //todo: move to onclick
//        if (LOAD_MONTHS - curMonthPos == 2) {
//            //todo: load next
//        } else if (currentIndex == 1) {
//            //todo: load prev
//        }
        val iterateMonth = (GregorianCalendar.getInstance() as GregorianCalendar).copyGregorian().apply {
            roll(GregorianCalendar.MONTH, -curMonthPos)
        }

        loadedMonths = ArrayList()
        for (i in 1..LOAD_MONTHS) {
            loadedMonths.add(monthRepository.getMonthData(iterateMonth))
            iterateMonth.roll(GregorianCalendar.MONTH, true)
        }

    }

    fun onClick() {
        viewState.test()
    }

}