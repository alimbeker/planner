package com.example.plannerproject.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plannerproject.HomeFragment
import com.example.plannerproject.data.CardData


class HomeFragmentView : ViewModel() {
     companion object {
         var cards:MutableList<CardData> = mutableListOf()
     }

     fun insert(task:String,aboutTask:String):MutableList<CardData> {
         cards.add(CardData(task,aboutTask))
        cards = cards
         return cards
     }

     fun loadCard(): List<CardData> {
         return cards
     }

}