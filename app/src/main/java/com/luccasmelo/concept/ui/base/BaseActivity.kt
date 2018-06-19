package com.luccasmelo.concept.ui.base

import android.support.v7.app.AppCompatActivity
import com.luccasmelo.concept.App
import com.luccasmelo.concept.di.component.ActivityComponent
import com.luccasmelo.concept.di.module.ActivityModule
import com.luccasmelo.concept.utils.unSafeLazy

abstract class BaseActivity:AppCompatActivity(){

    val activityComponent: ActivityComponent by unSafeLazy {
        getAppComponent() + ActivityModule(this)
   }

    private fun getAppComponent() = App.instance.applicationComponent

    protected abstract fun injectDependencies()

    protected abstract fun bind()

}