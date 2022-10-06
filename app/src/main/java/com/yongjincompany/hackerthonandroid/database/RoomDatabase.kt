package com.yongjincompany.hackerthonandroid.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.yongjincompany.hackerthonandroid.database.dao.CycleDao
import com.yongjincompany.hackerthonandroid.database.dao.DayStateDao
import com.yongjincompany.hackerthonandroid.database.entity.CycleEntity
import com.yongjincompany.hackerthonandroid.database.entity.DayStateEntity
import java.util.concurrent.Executors

@Database(
    entities = [CycleEntity::class, DayStateEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDatabase : androidx.room.RoomDatabase() {

    abstract fun cycleDao(): CycleDao
    abstract fun dayStateDao(): DayStateDao

    companion object {
        private var instance: RoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): RoomDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDatabase::class.java, "hackathon_database"
                )
                    .setQueryCallback({ sqlQuery, bindArgs ->
                        println("SQL Query: $sqlQuery SQL Args: $bindArgs")
                    }, Executors.newSingleThreadExecutor())
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
                return instance
            }
        }
    }
    