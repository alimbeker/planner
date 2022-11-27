package com.example.plannerproject.database

import androidx.lifecycle.LiveData
import androidx.room.*

// Data access object
@Dao
interface CardDao {

    //insert new CardEntity
    @Insert
    suspend fun insert(card: CardEntity)

//     get all Cards
    @Query("Select * from cardTable")
    fun getAll(): LiveData<MutableList<CardEntity>>

    @Delete
   suspend fun delete(card: CardEntity)

}