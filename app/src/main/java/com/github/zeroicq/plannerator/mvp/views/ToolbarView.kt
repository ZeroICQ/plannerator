package com.github.zeroicq.plannerator.mvp.views

import com.arellomobile.mvp.MvpView

interface ToolbarView: MvpView {
    fun setToolBarText(text: String)
}