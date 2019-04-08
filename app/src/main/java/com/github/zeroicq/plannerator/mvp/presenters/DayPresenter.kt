package com.github.zeroicq.plannerator.mvp.presenters

import android.icu.util.GregorianCalendar
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.mvp.views.DayView
import com.github.zeroicq.plannerator.repository.DayTestRepository
import com.github.zeroicq.plannerator.repository.MonthTestRepository
import javax.inject.Inject

@InjectViewState
class DayPresenter: MvpPresenter<DayView>() {
    // DI
    @Inject
    lateinit var app: PlanneratorApplication

    @Inject
    lateinit var dayRepository : DayTestRepository

    init {
        PlanneratorApplication.graph.inject(this)
    }

    fun getDayModel(date: GregorianCalendar) = dayRepository.getDayData(date)
}