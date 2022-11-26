package com.example.plannerproject.model

import android.app.Application
import android.media.CamcorderProfile.getAll
import androidx.lifecycle.*
import com.example.plannerproject.data.CardData
import com.example.plannerproject.database.CardDao
import com.example.plannerproject.database.CardDatabase
import com.example.plannerproject.database.CardEntity
import kotlinx.coroutines.launch


class HomeFragmentView( val database: CardDao , application: Application) : AndroidViewModel(application) {

    var card = MutableLiveData<CardData?>()
    val cardsLiveData = database.getAll()

    fun onClickInsert(task:String,aboutTask: String) {
        viewModelScope.launch {
            insert(task, aboutTask)
        }
    }

    //Delete one by one
    /*fun onClickDelete(entity: CardEntity) {
        viewModelScope.launch {
            val cardDao = CardDatabase.getInstance(getApplication())?.cardDao()
            cardDao?.delete(entity)
            database.getAll()
        }
    }*/

    fun onClear() {
        viewModelScope.launch {
            clear()
            card.value = null
        }
    }

    suspend fun clear() {
        database.clear()
    }



     suspend fun insert(task:String, aboutTask:String) {
         database.insert(CardEntity(task,aboutTask))
     }

     suspend fun delete(task: String, aboutTask: String) {
         database.delete(CardEntity(task,aboutTask))
     }






}