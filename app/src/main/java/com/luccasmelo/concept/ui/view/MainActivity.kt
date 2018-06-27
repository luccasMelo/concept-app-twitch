package com.luccasmelo.concept.ui.view

import android.databinding.BindingAdapter
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.luccasmelo.concept.R
import com.luccasmelo.concept.ui.base.BaseActivity
import com.luccasmelo.concept.utils.Constants
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.iconics.IconicsDrawable
import android.databinding.adapters.ListenerUtil
import android.support.v4.widget.SwipeRefreshLayout
import android.databinding.InverseBindingListener
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.luccasmelo.concept.data.model.Game
import android.support.v4.app.ActivityOptionsCompat
import android.content.Intent




class MainActivity : BaseActivity() {

    override fun bind() {
    }


    override fun injectDependencies() {
        activityComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, GameFragment.newInstance(), "fragment_game")
                    .commit()
        }

    }







}
