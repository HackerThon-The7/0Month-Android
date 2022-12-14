package com.yongjincompany.hackerthonandroid.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "day_state_table")
data class DayStateEntity(
    @PrimaryKey
    val date: String,
    val bodyState: String,
    val mood: String,
    val behaviorChange: String,

)