package com.example.plannerproject.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plannerproject.data.CardData

class HomeFragmentView : ViewModel() {
   private var cards = MutableLiveData<CardData>()


}