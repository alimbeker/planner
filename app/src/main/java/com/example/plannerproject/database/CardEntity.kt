package com.example.plannerproject.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "cardTable")
data class CardEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    @ColumnInfo(name="cardTask")
    val task:String,

    @ColumnInfo(name="cardDesc")
    val description:String


) : Parcelable
{
    // for add card like this
    @Ignore
    constructor(task:String,aboutTask: String):this(0,task,aboutTask)
}

