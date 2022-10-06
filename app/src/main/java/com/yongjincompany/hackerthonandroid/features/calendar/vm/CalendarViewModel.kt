package com.yongjincompany.hackerthonandroid.features.calendar.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yongjincompany.hackerthonandroid.database.RoomDatabase
import com.yongjincompany.hackerthonandroid.database.entity.DayStateEntity
import java.time.LocalDate
import java.util.*
import kotlinx.coroutines.launch

class CalendarViewModel : ViewModel() {

    val currentDate = MutableLiveData(LocalDate.now().toString())
    val info = MutableLiveData("입력된 정보가 없습니다.")
    val isWoman = MutableLiveData(true)

    val dayStateEntity = MutableLiveData<DayStateEntity?>()
    val dateList = MutableLiveData<List<String>>()

    var database: RoomDatabase? = null

    fun getStateDiaryByDate(date: String) {
        viewModelScope.launch {
            dayStateEntity.postValue(database?.dayStateDao()?.getDayState(date))
        }
    }


    fun getAllStateDate() {
        viewModelScope.launch {
            dateList.value = database?.dayStateDao()?.getAllDate()
        }
    }

}