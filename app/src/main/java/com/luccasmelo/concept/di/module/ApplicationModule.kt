package com.luccasmelo.concept.di.module

import android.app.Application
import com.luccasmelo.concept.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: App) {

    @Singleton
    @Provides
    fun provideApplication(): App = application


}