package com.example.plannerproject.data

import com.example.plannerproject.model.CardData



class Cardsource {

    companion object{
        var cards:MutableList<CardData> = mutableListOf()
    }

    fun insert(task:String,aboutTask:String):MutableList<CardData>{
        cards.add(CardData(task,aboutTask))
        Cardsource.cards= cards
        return cards
    }

    fun loadCard(): List<CardData> {
        return Cardsource.cards
    }

}