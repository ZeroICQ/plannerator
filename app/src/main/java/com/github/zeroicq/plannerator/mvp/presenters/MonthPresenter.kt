package com.github.zeroicq.plannerator.mvp.presenters

import android.app.Application
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.mvp.views.MonthView
import javax.inject.Inject

@InjectViewState
class MonthPresenter: MvpPresenter<MonthView>() {
    @Inject
    lateinit var app: Application

    init {
        PlanneratorApplication.graph.inject(this)
    }

    fun onClick() {
        viewState.test()
    }


}