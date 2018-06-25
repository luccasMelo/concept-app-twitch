package com.luccasmelo.concept.data.model

import com.google.gson.annotations.SerializedName

public class Game(val name: String, val popularity: Int, val localized_name: String) {
    lateinit var box: Box
}

public class GameContainer(public val game:Game){
    lateinit var viewers:String
    lateinit var channels:String

    override fun equals(other: Any?): Boolean {
        return hashCode() == (other as GameContainer).hashCode()
    }

    override fun hashCode(): Int {
        var result = game.hashCode()
        result = 31 * result + viewers.hashCode()
        result = 31 * result + channels.hashCode()
        result = 31 * result + game.name.hashCode()
        return result
    }


}

public class GameResponse(val top:List<GameContainer>)

public class Box(@SerializedName("large") val url: String)