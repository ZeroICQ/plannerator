package com.github.zeroicq.plannerator.mvp.views

import ru.terrakok.cicerone.Screen

interface BaseView {
    fun navigateTo(screen: Screen)
    fun newRootScreen(screen: Screen)
}