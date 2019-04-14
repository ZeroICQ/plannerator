package com.github.zeroicq.plannerator.mvp.views;

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.zeroicq.plannerator.mvp.models.EventModel

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface CreateEventView: MvpView {

    fun setModelData(eventModel: EventModel)
}
