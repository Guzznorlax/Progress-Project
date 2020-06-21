package com.ntuesoeoop.progressproject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProgressViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: ProgressRepo

    val allProgresses: LiveData<List<Progress>>

    init {
        val progressDao = ProgressDatabase.getDatabase(application, viewModelScope).progressDao()
        repo = ProgressRepo(progressDao)

        allProgresses = repo.allProgresses
    }

    fun insert(progress: Progress) = viewModelScope.launch(Dispatchers.IO) {
        // println("${progress.getName()} Inserted")
        repo.insert(progress)
    }

    fun update(progress: Progress) = viewModelScope.launch(Dispatchers.IO) {
        // println("${progress.getName()} Updated")
        repo.update(progress)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteAll()
    }

    fun evaluateAll() = viewModelScope.launch(Dispatchers.IO) {
        allProgresses.value?.forEach {
            it.evaluate()
            update(it)
        }
    }
}