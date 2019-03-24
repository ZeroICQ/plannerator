package com.github.zeroicq.plannerator.di

import android.app.Application
import android.util.MonthDisplayHelper
import com.github.zeroicq.plannerator.repository.MonthTestRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun provideApplication() = app

    @Provides
    @Singleton
    fun provideMonthRepository() = MonthTestRepository()


}