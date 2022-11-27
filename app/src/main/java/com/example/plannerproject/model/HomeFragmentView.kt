package com.example.plannerproject.model

import android.app.Application
import androidx.lifecycle.*
import com.example.plannerproject.database.CardDao
import com.example.plannerproject.database.CardEntity
import kotlinx.coroutines.launch


class HomeFragmentView( val database: CardDao , application: Application) : AndroidViewModel(application) {

    var cardsLiveData= database.getAll()
    fun onClickInsert(task:String,aboutTask: String) {
        viewModelScope.launch {
            insert(task, aboutTask)
        }
    }

    fun onClear() {
        viewModelScope.launch {
            clear()
        }
    }

    suspend fun clear() {
        database.clear()
    }



     suspend fun insert(task:String, aboutTask:String) {
         database.insert(CardEntity(task,aboutTask))
     }


    fun onSwipeDelete(card: CardEntity?){
        viewModelScope.launch {
            if (card != null) {
                delete(card)
            }
        }
    }

     suspend fun delete(card:CardEntity){
        database.delete(card)
    }



}