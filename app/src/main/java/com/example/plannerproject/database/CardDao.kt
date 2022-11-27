package com.example.plannerproject.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*


// Data access object
@Dao
interface CardDao {

    //insert new CardEntity
    @Insert
    suspend fun insert(card: CardEntity)

    @Query("DELETE FROM cardTable")
    suspend fun clear()

    @Query("UPDATE cardTable SET cardTask=:task,cardDesc=:aboutTask WHERE id LIKE :id")
    suspend fun update(task : String, aboutTask : String, id : Int)

    @Query("Select * from cardTable")
    fun getAll(): LiveData<MutableList<CardEntity>>

    @Delete
   suspend fun delete(card: CardEntity)

}