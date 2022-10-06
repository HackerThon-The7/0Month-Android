package com.yongjincompany.hackerthonandroid.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yongjincompany.hackerthonandroid.database.entity.CycleEntity

@Dao
interface CycleDao {

    @Query("Select * FROM cycle_table")
    suspend fun getAllCycle(): List<CycleEntity>

    @Query("Select * From cycle_table where startDate = :startDate")
    suspend fun getCycle(startDate: String): CycleEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCycle(entity: CycleEntity)

}