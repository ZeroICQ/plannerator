package com.github.zeroicq.plannerator

import android.app.Application
import com.github.zeroicq.plannerator.di.AppComponent
import com.github.zeroicq.plannerator.di.AppModule
import com.github.zeroicq.plannerator.di.DaggerAppComponent
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class PlanneratorApplication : Application() {
    companion object {
        lateinit var graph: AppComponent
        val appName = "Plannerator"
    }

    private val cicerone = Cicerone.create()

    val navigatorHolder = cicerone.navigatorHolder

    val router = cicerone.router

    override fun onCreate() {
        super.onCreate()
        graph = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}