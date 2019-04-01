package com.github.zeroicq.plannerator.ui.navigators

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen

enum class PlanneratorFragments {
    WEEK,
    MONTH
}

class MainNavigator(activity: FragmentActivity, containerId: Int) : SupportAppNavigator(activity, containerId) {
    override fun createFragment(screen: SupportAppScreen?): Fragment {
        return super.createFragment(screen)
    }
}