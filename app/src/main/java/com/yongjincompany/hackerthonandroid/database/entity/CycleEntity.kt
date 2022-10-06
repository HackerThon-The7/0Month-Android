package com.yongjincompany.hackerthonandroid.database.entity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "cycle_table")
data class CycleEntity(
    @PrimaryKey
    val startDate: String,
    val endDate: String?,

) {
    fun isPeriod(): Boolean {
        return endDate == null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getPeriodTerm(): Int {
        return endDate?.let {
            LocalDate.parse(startDate).until(LocalDate.parse(it)).days 
        } ?: LocalDate.parse(startDate).until(LocalDate.now()).days
    }
}
