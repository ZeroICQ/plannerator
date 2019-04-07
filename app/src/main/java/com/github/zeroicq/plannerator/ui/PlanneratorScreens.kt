package com.github.zeroicq.plannerator.ui

import android.support.v4.app.Fragment
import com.github.zeroicq.plannerator.ui.fragments.CreateEventFragment
import com.github.zeroicq.plannerator.ui.fragments.DayFragment
import com.github.zeroicq.plannerator.ui.fragments.MonthFragment
import com.github.zeroicq.plannerator.ui.fragments.WeekFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class WeekScreen: SupportAppScreen() {
    override fun getFragment() = WeekFragment()
}

class MonthScreen: SupportAppScreen() {
    override fun getFragment() = MonthFragment()
}

class DayScreen: SupportAppScreen() {
    override fun getFragment() = DayFragment()
}

class CreateEventScreen: SupportAppScreen() {
    override fun getFragment() = CreateEventFragment()
}