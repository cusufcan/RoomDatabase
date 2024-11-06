package com.mercan.roomdatabasemvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mercan.roomdatabasemvvm.data.database.TaskDatabase
import com.mercan.roomdatabasemvvm.data.model.Task
import com.mercan.roomdatabasemvvm.data.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TaskRepository
    val uncompletedTasks: LiveData<List<Task>>
    val completedTasks: LiveData<List<Task>>

    val task = MutableLiveData<Task>()

    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        uncompletedTasks = repository.uncompletedTasks
        completedTasks = repository.completedTasks
    }

    fun find(id: Int) = viewModelScope.launch {
        task.value = repository.find(id)
    }

    fun insert(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }

    fun update(task: Task) = viewModelScope.launch {
        repository.update(task)
    }

    fun delete(task: Task) = viewModelScope.launch {
        repository.delete(task)
    }
}