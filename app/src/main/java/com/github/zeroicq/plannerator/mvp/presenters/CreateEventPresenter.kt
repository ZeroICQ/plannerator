package com.github.zeroicq.plannerator.mvp.presenters

import android.icu.util.GregorianCalendar
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.mvp.models.EventModel
import com.github.zeroicq.plannerator.mvp.views.CreateEventView
import com.github.zeroicq.plannerator.util.copyGregorian
import javax.inject.Inject

@InjectViewState
class CreateEventPresenter : MvpPresenter<CreateEventView>() {
    // DI
    @Inject
    lateinit var app: PlanneratorApplication

    lateinit var event: EventModel

    init {
        PlanneratorApplication.graph.inject(this)
    }

    // must be called to initialize eventmodel
    fun setInitialTimeInterval(date: GregorianCalendar) {
        val eventEnd = date.copyGregorian().apply { add(GregorianCalendar.HOUR_OF_DAY, 1) }
        event = EventModel(date, eventEnd)

        viewState.setModelData(event)
    }

    fun updateEvent() {
        viewState.setModelData(event)
    }
}