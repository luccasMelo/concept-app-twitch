package com.luccasmelo.concept

import android.app.Application
import com.luccasmelo.concept.di.component.ApplicationComponent
import com.luccasmelo.concept.di.component.DaggerApplicationComponent
import com.luccasmelo.concept.di.module.ApplicationModule
import com.luccasmelo.concept.utils.unSafeLazy
import com.squareup.leakcanary.LeakCanary

public class App : Application() {

    companion object {
        lateinit var instance: App
    }

    val applicationComponent: ApplicationComponent by unSafeLazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }


    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) return
        LeakCanary.install(this)
        instance = this
    }
}