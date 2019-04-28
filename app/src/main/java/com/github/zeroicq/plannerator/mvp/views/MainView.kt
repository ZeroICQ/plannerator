package com.github.zeroicq.plannerator.mvp.views

import android.view.View
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MainView : MvpView, BaseView {
    fun test()
    fun setToolBarText(text: String)
    fun setFabOnclickListener(listener: (View) -> Unit)

    fun setShowCreateEventButton(show: Boolean)
}