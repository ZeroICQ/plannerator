package com.github.zeroicq.plannerator.mvp.presenters

import com.arellomobile.mvp.MvpPresenter
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.mvp.views.DayView
import javax.inject.Inject

class DayPresenter: MvpPresenter<DayView>() {
    // DI
    @Inject
    lateinit var app: PlanneratorApplication
    init {
        PlanneratorApplication.graph.inject(this)
    }
}