package com.example.plannerproject.model

import android.app.Application
import android.media.CamcorderProfile.getAll
import androidx.lifecycle.*
import com.example.plannerproject.database.CardDao
import com.example.plannerproject.database.CardEntity
import kotlinx.coroutines.launch


class HomeFragmentView( val database: CardDao , application: Application) : AndroidViewModel(application) {

    val cardsLiveData = database.getAll()

    fun onClickInsert(task:String,aboutTask: String) {
        viewModelScope.launch {
            insert(task, aboutTask)
        }
    }

     suspend fun insert(task:String, aboutTask:String) {
         database.insert(CardEntity(task,aboutTask))
     }




}