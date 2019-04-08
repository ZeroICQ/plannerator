package com.github.zeroicq.plannerator.di

import android.app.Application
import android.util.MonthDisplayHelper
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.mockup.StorageMockup
import com.github.zeroicq.plannerator.repository.DayTestRepository
import com.github.zeroicq.plannerator.repository.MonthTestRepository
import com.github.zeroicq.plannerator.repository.WeekTestRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: PlanneratorApplication) {

    @Provides
    @Singleton
    fun provideApplication(): PlanneratorApplication = app

    @Provides
    @Singleton
    fun provideMonthRepository() = MonthTestRepository()

    @Provides
    @Singleton
    fun provideWeekRepository() = WeekTestRepository()

    @Provides
    @Singleton
    fun provideDayRepository() = DayTestRepository()

    @Provides
    @Singleton
    fun provideStorageMockup() = StorageMockup()

}