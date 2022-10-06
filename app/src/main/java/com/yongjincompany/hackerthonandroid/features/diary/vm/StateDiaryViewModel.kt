package com.yongjincompany.hackerthonandroid.features.diary.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yongjincompany.hackerthonandroid.database.RoomDatabase
import com.yongjincompany.hackerthonandroid.database.entity.DayStateEntity
import kotlinx.coroutines.launch

class StateDiaryViewModel : ViewModel() {

    var database: RoomDatabase? = null

    fun saveState(date: String, bodyState: String, mood: String, behaviorChange: String) {
        viewModelScope.launch {
            database?.dayStateDao()?.insertDayState(
                DayStateEntity(
                    date,
                    bodyState = bodyState,
                    mood = mood,
                    behaviorChange = behaviorChange,
                )
            )
        }
    }

}