package com.luccasmelo.concept.di.component

import com.luccasmelo.concept.di.module.ActivityModule
import com.luccasmelo.concept.di.PerActivity
import com.luccasmelo.concept.ui.view.GameFragment
import com.luccasmelo.concept.ui.view.MainActivity
import com.luccasmelo.concept.ui.view_model.GameViewModel
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [(ActivityModule::class)])
interface ActivityComponent {

    fun inject(gameFragment: GameFragment)
    fun inject(mainActivity: MainActivity)
    fun inject(gameViewModel: GameViewModel)

}