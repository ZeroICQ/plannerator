package com.github.zeroicq.plannerator.mvp.presenters

import android.app.Application
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.mvp.views.MainView
import com.github.zeroicq.plannerator.ui.MonthScreen
import com.github.zeroicq.plannerator.ui.WeekScreen
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MainPresenter: MvpPresenter<MainView>() {
    // DI
    @Inject
    lateinit var app: PlanneratorApplication
    init {
        PlanneratorApplication.graph.inject(this)
    }

    fun onWeekCommandClick() = app.router.replaceScreen(WeekScreen())
    fun onMonthCommandClick() = app.router.replaceScreen(MonthScreen())

}