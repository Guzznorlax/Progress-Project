package com.ntuesoeoop.progressproject

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData


class ProgressRepo(private val progressDao: ProgressDao){

    val allProgresses: LiveData<List<Progress>> = progressDao.getAll()

    @WorkerThread
    suspend fun insert(progress: Progress){
        progressDao.insert(progress)
    }

    @WorkerThread
    suspend fun update(progress: Progress){
        progressDao.update(progress)
    }
}