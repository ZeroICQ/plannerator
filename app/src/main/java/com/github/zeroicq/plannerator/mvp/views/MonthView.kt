package com.github.zeroicq.plannerator.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MonthView : MvpView, ToolbarView, BaseView {
    fun onRecyclerAdvance(amount: Int)
    fun onRecyclerPrev(amount: Int)
}
