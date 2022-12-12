package com.example.plannerproject.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.plannerproject.api.MarsApiService
import com.example.plannerproject.database.CardDao
import com.example.plannerproject.database.CardEntity
import kotlinx.coroutines.launch


class HomeFragmentView( val database: CardDao , application: Application) : AndroidViewModel(application) {

    private val _property = MutableLiveData<List<CardEntity>>()
    val property:LiveData<List<CardEntity>> = _property

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
        viewModelScope.launch {
            getMarsRealEstateProperties()
        }
    }

    fun onClear() {
        viewModelScope.launch {
            clear()
        }
    }

    suspend fun clear() {
        database.clear()
        MarsApiService.MarsApi.retrofitService.deleteAll();
        getMarsRealEstateProperties()
    }

    suspend fun insert(task:String, aboutTask:String) {
         database.insert(CardEntity(task,aboutTask))
        MarsApiService.MarsApi.retrofitService.addTask(CardEntity(task,aboutTask))
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
            Log.d("RESPONCE",_response.value.toString())
        }catch (e: Exception) {
            Log.d("ERROR",e.toString())
            _response.value = "Failure: ${e.message}"
        }
    }
}