package com.github.zeroicq.plannerator.mvp.presenters

import android.icu.util.GregorianCalendar
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.mockup.StorageMockup
import com.github.zeroicq.plannerator.mvp.models.EventModel
import com.github.zeroicq.plannerator.mvp.views.CreateEventView
import com.github.zeroicq.plannerator.util.copyGregorian
import javax.inject.Inject
import android.view.WindowManager
import android.widget.EditText
import android.content.Context.INPUT_METHOD_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


@InjectViewState
class CreateEventPresenter : MvpPresenter<CreateEventView>() {
    // DI
    @Inject
    lateinit var app: PlanneratorApplication

    @Inject
    lateinit var storage : StorageMockup

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

    fun saveEvent() {
        storage.addEvent(event)
        app.router.exit()
    }
}