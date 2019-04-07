package com.github.zeroicq.plannerator.mvp.presenters

import com.arellomobile.mvp.MvpPresenter
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.mvp.views.CreateEventView
import javax.inject.Inject

class CreateEventPresenter : MvpPresenter<CreateEventView>() {
    // DI
    @Inject
    lateinit var app: PlanneratorApplication
    init {
        PlanneratorApplication.graph.inject(this)
    }
}