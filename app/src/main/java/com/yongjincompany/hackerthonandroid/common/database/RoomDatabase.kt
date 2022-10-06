package com.yongjincompany.hackerthonandroid.common.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.yongjincompany.hackerthonandroid.features.home.database.entity.CycleEntity
import com.yongjincompany.hackerthonandroid.features.home.database.entity.DayStateEntity
import java.util.concurrent.Executors

@Database(
    entities = [CycleEntity::class, DayStateEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDatabase : androidx.room.RoomDatabase() {

//    abstract fun tokenDao(): TokenDao

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
    