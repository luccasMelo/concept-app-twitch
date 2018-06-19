package com.luccasmelo.concept.ui.base

import android.content.Context
import android.support.v4.app.Fragment
import com.luccasmelo.concept.di.component.ActivityComponent

public abstract class BaseFragment:Fragment(){
    lateinit var activityComponent: ActivityComponent
    lateinit var activity: BaseActivity


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is BaseActivity){
            activity  = context
            activityComponent = activity.activityComponent
            injectDependencies()
        }

    }


    protected abstract fun injectDependencies()

    protected abstract fun bind()

}