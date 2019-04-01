package com.github.zeroicq.plannerator.mvp.models

class WeekModel(val days: ArrayList<DayModel>) {
    val firstDay get() = days[0]
    val date get() = firstDay.date
}