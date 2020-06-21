package com.ntuesoeoop.progressproject


import androidx.room.*
import androidx.lifecycle.LiveData


@Dao
interface ProgressDao {

    @Query("SELECT * FROM progress")
    fun getAll(): LiveData<List<Progress>>

    @Query("SELECT * FROM progress WHERE id = :id")
    fun getById(id: Int): LiveData<Progress>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(progress: Progress): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(progress: Progress)

    @Delete
    fun delete(progress: Progress)

    @Query("DELETE FROM progress")
    fun deleteAll()
}