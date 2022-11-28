package com.example.plannerproject.model

import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import androidx.lifecycle.*
import com.example.plannerproject.database.CardDao
import com.example.plannerproject.database.CardEntity
import kotlinx.coroutines.launch


class HomeFragmentView( val database: CardDao , application: Application) : AndroidViewModel(application) {

    private val cardsLiveData= database.getAll()

    private val text = MutableLiveData("")

    private val _filteredCards = MediatorLiveData<List<CardEntity>>().apply {
        addSource(cardsLiveData) {
            combineLatestData()
        }
        addSource(text) {
            combineLatestData()
        }
    }
    val filteredCards: LiveData<List<CardEntity>> = _filteredCards

    private fun combineLatestData() {
        val latestCards = cardsLiveData.value ?: emptyList()
        val latestText = text.value ?: ""
        val filteredCards = latestCards.filter { it.task.contains(latestText, ignoreCase = true) }
        _filteredCards.value = filteredCards
    }

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

    fun onQueryTextChange(text: String?) {
        this.text.value = text
    }
}