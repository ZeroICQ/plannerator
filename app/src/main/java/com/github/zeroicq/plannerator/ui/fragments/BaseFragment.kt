package com.github.zeroicq.plannerator.ui.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.R
import com.github.zeroicq.plannerator.databinding.ActivityMainBinding
import com.github.zeroicq.plannerator.databinding.FragmentMonthBinding
import com.github.zeroicq.plannerator.mvp.views.BaseView
import com.github.zeroicq.plannerator.mvp.views.MainView
import com.github.zeroicq.plannerator.mvp.views.ToolbarView
import com.github.zeroicq.plannerator.ui.MainActivity
import ru.terrakok.cicerone.Screen
import javax.inject.Inject

open class BaseFragment: MvpAppCompatFragment(), ToolbarView, BaseView {
    // DI
    @Inject
    lateinit var app: PlanneratorApplication

    protected lateinit var activityView: MainView

    protected var showFab: Boolean = false
        set(value) {
            field = value
            activityView.setShowCreateEventButton(value)
        }

    init {
        PlanneratorApplication.graph.inject(this)
        Log.d(PlanneratorApplication.appName, showFab.toString())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = super.onCreateView(inflater, container, savedInstanceState)
        activityView = (activity as MainView)
        showFab = false
        return v
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