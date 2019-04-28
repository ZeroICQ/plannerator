package com.github.zeroicq.plannerator.mvp.presenters

import android.util.Log
import android.view.View
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.mvp.models.EventModel
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

    fun onEditButtonClick(e: EventModel) {
        Log.d(PlanneratorApplication.appName, "edit was clicked")
    }

    fun onDeleteButtonClick(e: EventModel) {
        Log.d(PlanneratorApplication.appName, "delete was clicked")
    }
}