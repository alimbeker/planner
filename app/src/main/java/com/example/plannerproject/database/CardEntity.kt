package com.example.plannerproject.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cardTable")
data class CardEntity (
    @PrimaryKey(autoGenerate = true)
     val id:Int,

    @ColumnInfo(name="cardTask")
    val task:String,

    @ColumnInfo(name="cardDesc")
    val aboutTask:String

)
{
    // for add card like this
    constructor(task:String,aboutTask: String):this(0,task,aboutTask)
}
