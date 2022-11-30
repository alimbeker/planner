package com.example.plannerproject.model

import MarsProperty
import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import android.util.Log
import androidx.lifecycle.*
import com.example.plannerproject.api.MarsApiService
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
    //Retrofit
    private val _property = MutableLiveData<List<MarsProperty>>()
    val property:LiveData<List<MarsProperty>> = _property

    private val _response = MutableLiveData<String>()
    val response: LiveData<String> = _response

    init {
        viewModelScope.launch {
            getMarsRealEstateProperties()
        }
    }

    private suspend fun getMarsRealEstateProperties() {
        try {
            val listResult = MarsApiService.MarsApi.retrofitService.getProperties()
            _response.value = "Success: ${listResult.size} Mars properties retrieved"
            _property.value = listResult
        } catch (e: Exception) {
            _response.value = "Failure: ${e.message}"
        }
    }

}