package com.ntuesoeoop.progressproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [(Progress::class)], version = 6)
abstract class ProgressDatabase : RoomDatabase() {

    abstract fun progressDao(): ProgressDao

    //    private var instance: ProgressDatabase? = null
    companion object {
        @Volatile
        private var INSTANCE: ProgressDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): ProgressDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProgressDatabase::class.java,
                    "progress"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(DatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class DatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.progressDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         */
        fun populateDatabase(progressDao: ProgressDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            progressDao.deleteAll()

            var progress = Progress("早起", description = "每天都要早起", createdAt = "2020-06-01 14:57:14.283+0800", updatedAt = "2020-06-20 14:57:14.283+0800",isCompleted = true, totalCompleted = 20, streak = 8, maxStreak = 10, passedPeriod = 22)
            progressDao.insert(progress)

            progress = Progress("物件導向", period = 7, targetCompleted = 1, description = "看物件導向NTU COOL影片", createdAt = "2020-03-24 14:57:14.283+0800", updatedAt = "2020-06-20 14:57:14.283+0800", isCompleted = true, streak = 12, maxStreak = 12, passedPeriod = 12, totalCompleted = 12)
            progressDao.insert(progress)

            progress = Progress("跑步", period = 7, targetCompleted = 3, description = "我太胖了我需要減肥", createdAt = "2020-05-12 14:57:14.283+0800", updatedAt = "2020-06-20 14:57:14.283+0800", isCompleted = true, useTargetNum = true, targetNum = 10, passedPeriod = 6, currentCompleted = 2, streak = 2, maxStreak = 3, totalCompleted = 5, count = 160, currentNum = 5)
            progressDao.insert(progress)
        }
    }


}