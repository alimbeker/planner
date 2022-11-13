package com.example.plannerproject.model

import com.example.plannerproject.data.CardData


class Cardsource {

    companion object{
        var cards:MutableList<CardData> = mutableListOf()
    }

    fun insert(task:String,aboutTask:String):MutableList<CardData>{
        cards.add(CardData(task,aboutTask))
       cards = cards
        return cards
    }

    fun loadCard(): List<CardData> {
        return cards
    }

}