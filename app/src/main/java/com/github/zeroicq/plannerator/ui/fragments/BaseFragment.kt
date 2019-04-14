package com.github.zeroicq.plannerator.ui.fragments

import com.arellomobile.mvp.MvpAppCompatFragment
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.mvp.views.BaseView
import com.github.zeroicq.plannerator.mvp.views.MainView
import com.github.zeroicq.plannerator.mvp.views.ToolbarView
import ru.terrakok.cicerone.Screen
import javax.inject.Inject

open class BaseFragment: MvpAppCompatFragment(), ToolbarView, BaseView {
    // DI
    @Inject
    lateinit var app: PlanneratorApplication

    init {
        PlanneratorApplication.graph.inject(this)
    }

    override fun setToolBarText(text: String) {
        (activity as MainView).setToolBarText(text)
    }

    override fun navigateTo(screen: Screen) {
        app.router.navigateTo(screen)
    }

    override fun newRootScreen(screen: Screen) {
        app.router.newRootScreen(screen)
    }
}