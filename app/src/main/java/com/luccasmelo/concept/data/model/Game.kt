package com.luccasmelo.concept.data.model

public class Game(val name: String, val popularity: Int, val localized_name: String) {
    lateinit var box: Box
}

public class Box(val template: String)