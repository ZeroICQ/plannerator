package com.github.zeroicq.plannerator.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.mvp.views.EventView
import javax.inject.Inject

@InjectViewState
class EventPresenter: MvpPresenter<EventView>() {
    // DI
    @Inject
    lateinit var app: PlanneratorApplication
    init {
        PlanneratorApplication.graph.inject(this)
    }
}