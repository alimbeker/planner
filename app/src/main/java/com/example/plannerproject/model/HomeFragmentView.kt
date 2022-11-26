package com.example.plannerproject.model

import android.app.Application
import androidx.lifecycle.*
import com.example.plannerproject.data.CardData
import com.example.plannerproject.database.CardDatabase
import com.example.plannerproject.database.CardEntity
import kotlinx.coroutines.launch


class HomeFragmentView(private val database:CardDatabase,application: Application) : AndroidViewModel(application) {

    val cardsLiveData = database.cardDatabaseDao.getAll()

    fun onClickInsert() {
        viewModelScope.launch {
            insert("", "")
        }
    }

     suspend fun insert(task:String, aboutTask:String) {
         database.cardDatabaseDao.insert(CardEntity(task,aboutTask))
     }




}