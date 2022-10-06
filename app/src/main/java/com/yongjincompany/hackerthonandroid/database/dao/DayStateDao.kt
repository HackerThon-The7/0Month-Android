package com.yongjincompany.hackerthonandroid.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yongjincompany.hackerthonandroid.database.entity.CycleEntity
import com.yongjincompany.hackerthonandroid.database.entity.DayStateEntity

@Dao
interface DayStateDao {

    @Query("Select * FROM day_state_table")
    suspend fun getAllDayState(): List<DayStateEntity>

    @Query("Select * From day_state_table where date = :date")
    suspend fun getDayState(date: String): DayStateEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDayState(entity: DayStateEntity)

}