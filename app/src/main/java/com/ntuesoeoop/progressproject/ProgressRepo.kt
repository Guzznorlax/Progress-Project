package com.ntuesoeoop.progressproject

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData


class ProgressRepo(private val progressDao: ProgressDao) {

    val allProgresses: LiveData<List<Progress>> = progressDao.getAll()

    @WorkerThread
    suspend fun getById(id: Int): LiveData<Progress> {
        return progressDao.getById(id)
    }

    @WorkerThread
    suspend fun insert(progress: Progress) {
        progressDao.insert(progress)
    }

    @WorkerThread
    suspend fun update(progress: Progress) {
        progressDao.update(progress)
    }

    @WorkerThread
    suspend fun delete(progress: Progress) {
        progressDao.delete(progress)
    }

    @WorkerThread
    suspend fun deleteAll() {
        progressDao.deleteAll()
    }

}