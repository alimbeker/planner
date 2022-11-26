package com.example.plannerproject.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.plannerproject.data.CardData

@Entity(tableName = "cardTable")
data class CardEntity (

    @ColumnInfo(name="cardTask")
    val task:String,

    @ColumnInfo(name="cardDesc")
    val aboutTask:String

)
