package com.github.zeroicq.plannerator.di

import com.github.zeroicq.plannerator.mvp.presenters.*
import com.github.zeroicq.plannerator.repository.DayTestRepository
import com.github.zeroicq.plannerator.repository.MonthTestRepository
import com.github.zeroicq.plannerator.repository.WeekTestRepository
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
    fun inject(eventPresenter: EventPresenter)

    // test repos
    fun inject(weekTestRepository: WeekTestRepository)
    fun inject(monthTestRepository: MonthTestRepository)
    fun inject(dayTestRepository: DayTestRepository)

}