package com.github.zeroicq.plannerator.mvp.models

class MonthModel(val days: ArrayList<DayModel>) {
    val firstDay get() = days[0]
}
