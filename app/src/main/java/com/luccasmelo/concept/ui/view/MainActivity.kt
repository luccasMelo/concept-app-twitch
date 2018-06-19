package com.luccasmelo.concept.ui.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.luccasmelo.concept.R
import com.luccasmelo.concept.ui.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun bind() {
        TODO()
    }

    override fun injectDependencies() {
        activityComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, GameFragment.newInstance())
                    .commitNow()
        }
    }

}
