package com.github.zeroicq.plannerator.ui

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import android.os.Bundle
import android.support.v4.app.BundleCompat
import android.support.v4.app.Fragment
import com.github.zeroicq.plannerator.ui.fragments.*
import ru.terrakok.cicerone.android.support.SupportAppScreen

class WeekScreen: SupportAppScreen() {
    override fun getFragment() = WeekFragment()
}

class MonthScreen: SupportAppScreen() {
    override fun getFragment() = MonthFragment()
}

class DayScreen(val date: GregorianCalendar? = null): SupportAppScreen() {
    override fun getFragment(): Fragment {
        val f = DayFragment()

        if (date != null) {
            val bundle = Bundle()
            bundle.putLong(DayFragment.BUNDLE_KEYS.DATE.toString(), date.timeInMillis)
            f.arguments = bundle
        }

        return f
    }
}

class CreateEventScreen: SupportAppScreen() {
    override fun getFragment() = CreateEventFragment()
}

class EventScreen: SupportAppScreen() {
    override fun getFragment() = EventFragment()
}