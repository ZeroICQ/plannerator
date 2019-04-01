package com.github.zeroicq.plannerator.ui

import android.support.v4.app.Fragment
import com.github.zeroicq.plannerator.ui.fragments.MonthFragment
import com.github.zeroicq.plannerator.ui.fragments.WeekFragment
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppScreen

class WeekScreen: SupportAppScreen() {
    override fun getFragment() = WeekFragment()
}

class MonthScreen: SupportAppScreen() {
    override fun getFragment() = MonthFragment()
}