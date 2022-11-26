package com.example.plannerproject.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.plannerproject.data.CardData

@Dao
interface CardDao {

    @Insert
    suspend fun insert(card: CardEntity)

    @Delete
    suspend fun delete(card: CardEntity)

    @Query("DELETE FROM cardTable")
    suspend fun clear()


    /*@Update
    suspend fun update(card: CardEntity?)*/

    @Query("UPDATE cardTable SET cardTask=:task,cardDesc=:aboutTask WHERE id LIKE :id")
    suspend fun update(task : String, aboutTask : String, id : Int)


    @Query("Select * from cardTable")
    fun getAll(): LiveData<List<CardEntity>>

}