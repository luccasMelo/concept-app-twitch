package com.luccasmelo.concept.di.component

import com.luccasmelo.concept.di.PerActivity
import com.luccasmelo.concept.di.module.ActivityModule
import com.luccasmelo.concept.di.module.ApiModule
import com.luccasmelo.concept.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class),(ApiModule::class)])
interface ApplicationComponent {
    operator fun plus(activityModule: ActivityModule): ActivityComponent
}