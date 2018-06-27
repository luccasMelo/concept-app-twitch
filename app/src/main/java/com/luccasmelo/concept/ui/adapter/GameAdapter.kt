package com.luccasmelo.concept.ui.adapter

import android.support.v4.view.ViewCompat
import android.view.View
import android.widget.ImageView
import com.luccasmelo.concept.BR
import com.luccasmelo.concept.R
import com.luccasmelo.concept.data.model.GameContainer
import com.luccasmelo.kotlinutils.GenericAdapter

public class GameAdapter(val games:ArrayList<GameContainer>):GenericAdapter<GameContainer>(games, R.layout.item_game, BR.gameContainer){



    lateinit var itemClickListener: (itemView: View, pos:Int)->(Unit)


    override fun onItemClick(itemView: View, position: Int) {
        super.onItemClick(itemView, position)
        itemClickListener.invoke(itemView, position)
    }

   public override fun update(items: ArrayList<GameContainer>) {
        super.update(items)
    }


    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        ViewCompat.setTransitionName(holder.binding.root.findViewById<ImageView>(R.id.image), "${position}_image")

    }


}

