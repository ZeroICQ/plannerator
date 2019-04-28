package com.github.zeroicq.plannerator.mvp.presenters

import android.icu.util.GregorianCalendar
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.mvp.models.EventModel
import com.github.zeroicq.plannerator.mvp.views.DayView
import com.github.zeroicq.plannerator.repository.DayTestRepository
import com.github.zeroicq.plannerator.repository.MonthTestRepository
import com.github.zeroicq.plannerator.ui.CreateEventScreen
import com.github.zeroicq.plannerator.ui.EventScreen
import javax.inject.Inject

@InjectViewState
class DayPresenter: MvpPresenter<DayView>() {
    // DI
    @Inject
    lateinit var app: PlanneratorApplication

    @Inject
    lateinit var dayRepository : DayTestRepository

    lateinit var date: GregorianCalendar

    init {
        PlanneratorApplication.graph.inject(this)
    }

    fun getDayModel(date: GregorianCalendar) = dayRepository.getDayData(date)

    //todo: pass event data
    fun onEventClick(it: EventModel) {
        app.router.navigateTo(EventScreen(it))
    }

    fun onCreateEvent() {
        app.router.navigateTo(CreateEventScreen(date))
    }
}