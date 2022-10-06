package com.yongjincompany.hackerthonandroid.features.home.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DayStateEntity(
    @PrimaryKey
    val date: String,
    val bodyState: String,
    val mood: String,
    val behaviorChange: String,

)