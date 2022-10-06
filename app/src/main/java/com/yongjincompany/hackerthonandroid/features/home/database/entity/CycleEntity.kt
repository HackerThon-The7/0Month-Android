package com.yongjincompany.hackerthonandroid.features.home.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CycleEntity(
    @PrimaryKey
    val startDate: String,
    val endDate: String,
    val isPerioding: Boolean,

)
