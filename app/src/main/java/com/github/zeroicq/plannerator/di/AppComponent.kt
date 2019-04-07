package com.github.zeroicq.plannerator.di

import com.github.zeroicq.plannerator.mvp.presenters.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(monthPresenter : MonthPresenter)
    fun inject(mainPresenter : MainPresenter)
    fun inject(weekPresenter: WeekPresenter)
    fun inject(dayPresenter: DayPresenter)
    fun inject(createEventPresenter: CreateEventPresenter)

}