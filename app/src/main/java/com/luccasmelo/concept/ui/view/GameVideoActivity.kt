package com.luccasmelo.concept.ui.view

import android.os.Bundle
import android.provider.SyncStateContract
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.luccasmelo.concept.R
import com.luccasmelo.concept.utils.Constants
import kotlinx.android.synthetic.main.activity_game_video.*

public class GameVideoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_video)
        Glide.with(this)
                .load(intent.extras!![Constants.GAME_URL_EXTRA] as String)
                .into(findViewById(R.id.place_holder))

        toolbar.title = intent.extras!![Constants.GAME_NAME_EXTRA] as String


    }


}