package com.github.zeroicq.plannerator

import android.app.Application
import com.github.zeroicq.plannerator.di.AppComponent
import com.github.zeroicq.plannerator.di.AppModule
import com.github.zeroicq.plannerator.di.DaggerAppComponent

class PlanneratorApplication : Application() {
    companion object {
        lateinit var graph: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        graph = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}