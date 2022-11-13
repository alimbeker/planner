package com.example.plannerproject.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plannerproject.HomeFragment
import com.example.plannerproject.data.CardData


class HomeFragmentView : ViewModel() {
   /*private val cards = ArrayList<CardData>()
   private val cardslivedata = MutableLiveData<List<CardData>>()

   fun insert(task:String,aboutTask:String): MutableLiveData<List<CardData>> {
     cards.add(CardData(task,aboutTask))
     cardslivedata.value = cards
     return cardslivedata
   }

   fun loadCard(): MutableLiveData<List<CardData>> {
       return cardslivedata
   }*/

   companion object {
       var cards:MutableList<CardData> = mutableListOf()
   }

    fun insert(task:String,aboutTask:String):MutableList<CardData> {
      cards.add(CardData(task,aboutTask))
      HomeFragmentView.cards = cards
       return HomeFragmentView.cards
   }

    fun loadCard(): List<CardData> {
        return HomeFragmentView.cards
    }

}