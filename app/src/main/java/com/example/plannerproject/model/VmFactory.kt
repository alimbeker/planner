package com.example.plannerproject.model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plannerproject.database.CardDao

class VmFactory(
    private val dataSource:CardDao,
    private val application: Application):ViewModelProvider.Factory {
 @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeFragmentView::class.java)) {
            return HomeFragmentView(dataSource,application) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }


}