package com.github.zeroicq.plannerator.mvp.presenters

import android.icu.util.GregorianCalendar
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.mvp.models.WeekModel
import com.github.zeroicq.plannerator.mvp.views.WeekView
import com.github.zeroicq.plannerator.repository.WeekTestRepository
import com.github.zeroicq.plannerator.util.copyGregorian
import javax.inject.Inject

@InjectViewState
class WeekPresenter: MvpPresenter<WeekView>() {
    // DI
    @Inject
    lateinit var app: PlanneratorApplication

    @Inject
    lateinit var monthRepository : WeekTestRepository

    // properties
    lateinit var loadedWeeks: ArrayList<WeekModel>

    private val LOAD_WEEKS = 10

    private val LOAD_THRESHOLD = 1
    private val LOAD_AMOUNT = 2

    var curWeekPos = LOAD_WEEKS / 2


    init {
        PlanneratorApplication.graph.inject(this)
        loadWeeks()
    }

    private fun loadWeeks() {
        val iterateWeek = (GregorianCalendar.getInstance() as GregorianCalendar).copyGregorian().apply {
            add(GregorianCalendar.WEEK_OF_YEAR, -curWeekPos)
        }

        loadedWeeks = ArrayList()
        for (i in 1..LOAD_WEEKS) {
            loadedWeeks.add(monthRepository.getWeekData(iterateWeek))
            iterateWeek.add(GregorianCalendar.WEEK_OF_YEAR, 1)
        }
//        viewState.setToolBarText(getMonthWithYearString(app.applicationContext, loadedWeeks[curWeekPos].date))
    }

    fun onClick() {
//        viewState.test()
    }

    fun onMonthPosChange(pos: Int) {
        Log.d("Plannerator", "week snap pos changed to $pos")
        curWeekPos = pos+1
//        viewState.setToolBarText(getMonthWithYearString(app.applicationContext, loadedWeeks[curWeekPos].date))
        if (LOAD_WEEKS - 1 - curWeekPos == LOAD_THRESHOLD ) {
            for (i in 1..LOAD_AMOUNT) {
                loadedWeeks.add(monthRepository.getWeekData(loadedWeeks.last().date.copyGregorian().apply {
                    add(GregorianCalendar.WEEK_OF_YEAR, 1)
                }))
                loadedWeeks.removeAt(0)
            }

            viewState.onRecyclerAdvance(LOAD_AMOUNT)

        } else if (curWeekPos == LOAD_THRESHOLD) {
            for (i in 1..LOAD_AMOUNT) {
                loadedWeeks.add(0, monthRepository.getWeekData(loadedWeeks.first().date.copyGregorian().apply {
                    add(GregorianCalendar.WEEK_OF_YEAR, -1)
                }))
                loadedWeeks.removeAt(loadedWeeks.lastIndex)
            }
            viewState.onRecyclerPrev(LOAD_AMOUNT)
        }

    }

}