package com.github.zeroicq.plannerator.di

import com.github.zeroicq.plannerator.mvp.presenters.MainPresenter
import com.github.zeroicq.plannerator.mvp.presenters.MonthPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(monthPresenter : MonthPresenter)
    fun inject(mainPresenter : MainPresenter)

}