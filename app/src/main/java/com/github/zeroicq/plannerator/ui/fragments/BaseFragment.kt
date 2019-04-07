package com.github.zeroicq.plannerator.ui.fragments

import com.arellomobile.mvp.MvpAppCompatFragment
import com.github.zeroicq.plannerator.mvp.views.MainView
import com.github.zeroicq.plannerator.mvp.views.ToolbarView

open class BaseFragment: MvpAppCompatFragment(), ToolbarView {
    override fun setToolBarText(text: String) {
        (activity as MainView).setToolBarText(text)
    }
}