package com.ntuesoeoop.progressproject

import android.database.Observable
import androidx.room.*
import java.util.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

@Dao
interface ProgressDao {

    @Query("SELECT * FROM progress")
    fun getAll(): LiveData<List<Progress>>

    @Query("SELECT * FROM progress WHERE id = :id")
    fun getById(id: String): LiveData<Progress>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(progress: Progress): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(progress: Progress)

    @Delete
    fun delete(progress: Progress)
}