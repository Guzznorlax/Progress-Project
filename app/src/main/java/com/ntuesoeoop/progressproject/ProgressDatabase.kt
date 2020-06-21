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

            var progress = Progress("Hello", createdAt = "2020-06-18 14:57:14.283+0800",isCompleted = true)
            progressDao.insert(progress)

            progress = Progress("World!", period = 1, description = "aaaa", createdAt = "2020-06-20 14:57:14.283+0800", isCompleted = true)
            progressDao.insert(progress)

            progress = Progress("Number MODE!", period = 8, description = "aaaa", createdAt = "2020-06-19 14:57:14.283+0800", isCompleted = true, useTargetNum = true, targetNum = 11)
            progressDao.insert(progress)
        }
    }


}