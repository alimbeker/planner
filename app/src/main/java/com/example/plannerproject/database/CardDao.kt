package com.example.plannerproject.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.plannerproject.data.CardData

@Dao
interface CardDao {

    @Insert
    suspend fun insert(card: CardEntity)

    @Query("Select * from cardTable")
    fun getAll(): LiveData<List<CardEntity>>

}