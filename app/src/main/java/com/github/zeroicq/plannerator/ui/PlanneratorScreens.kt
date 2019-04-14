package com.github.zeroicq.plannerator.ui

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import android.os.Bundle
import android.support.v4.app.BundleCompat
import android.support.v4.app.Fragment
import com.github.zeroicq.plannerator.mvp.models.EventModel
import com.github.zeroicq.plannerator.ui.fragments.*
import com.github.zeroicq.plannerator.util.putGregorianCalendar
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
            bundle.putGregorianCalendar(DayFragment.BUNDLE_KEYS.DATE.toString(), date)
            f.arguments = bundle
        }

        return f
    }
}

class CreateEventScreen(val date: GregorianCalendar): SupportAppScreen() {
    override fun getFragment(): Fragment {
        val f = CreateEventFragment()
        val bundle = Bundle()
        bundle.putGregorianCalendar(CreateEventFragment.BUNDLE_KEYS.DATE.toString(), date)
        f.arguments = bundle

        return f
    }

}

class EventScreen(val event: EventModel): SupportAppScreen() {
    override fun getFragment(): Fragment {
        val f = EventFragment()

        val bundle = Bundle()
        bundle.putParcelable(EventFragment.BUNDLE_KEYS.EVENT.toString(), event)
        f.arguments = bundle
        return f
    }
}