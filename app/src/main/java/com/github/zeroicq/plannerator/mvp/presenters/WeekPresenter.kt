package com.github.zeroicq.plannerator.mvp.presenters

import android.icu.util.GregorianCalendar
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.mvp.models.EventModel
import com.github.zeroicq.plannerator.mvp.models.WeekModel
import com.github.zeroicq.plannerator.mvp.views.WeekView
import com.github.zeroicq.plannerator.repository.WeekTestRepository
import com.github.zeroicq.plannerator.ui.EventScreen
import com.github.zeroicq.plannerator.util.copyGregorian
import com.github.zeroicq.plannerator.util.getMonthWithYearString
import javax.inject.Inject

@InjectViewState
class WeekPresenter: MvpPresenter<WeekView>() {
    // DI
    @Inject
    lateinit var app: PlanneratorApplication

    @Inject
    lateinit var weekRepository : WeekTestRepository

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
            loadedWeeks.add(weekRepository.getWeekData(iterateWeek))
            iterateWeek.add(GregorianCalendar.WEEK_OF_YEAR, 1)
        }
        viewState.setToolBarText(getMonthWithYearString(app.applicationContext, loadedWeeks[curWeekPos].date))
    }

    fun onClick() {
//        viewState.test()
    }

    fun onMonthPosChange(pos: Int) {
        Log.d(PlanneratorApplication.appName, "week snap pos changed to $pos")
        curWeekPos = pos
        viewState.setToolBarText(getMonthWithYearString(app.applicationContext, loadedWeeks[curWeekPos].date))
        if (LOAD_WEEKS - 1 - curWeekPos == LOAD_THRESHOLD ) {
            for (i in 1..LOAD_AMOUNT) {
                loadedWeeks.add(weekRepository.getWeekData(loadedWeeks.last().date.copyGregorian().apply {
                    add(GregorianCalendar.WEEK_OF_YEAR, 1)
                }))
                loadedWeeks.removeAt(0)
            }

            viewState.onRecyclerAdvance(LOAD_AMOUNT)

        } else if (curWeekPos == LOAD_THRESHOLD) {
            for (i in 1..LOAD_AMOUNT) {
                loadedWeeks.add(0, weekRepository.getWeekData(loadedWeeks.first().date.copyGregorian().apply {
                    add(GregorianCalendar.WEEK_OF_YEAR, -1)
                }))
                loadedWeeks.removeAt(loadedWeeks.lastIndex)
            }
            viewState.onRecyclerPrev(LOAD_AMOUNT)
        }

    }
    // todo: pass event data
    fun onEventClick(it: EventModel) {
        app.router.navigateTo(EventScreen(it))
    }


}