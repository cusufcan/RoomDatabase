package com.mercan.roomdatabasemvvm.data.repository

import androidx.lifecycle.LiveData
import com.mercan.roomdatabasemvvm.data.database.TaskDao
import com.mercan.roomdatabasemvvm.data.model.Task

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: LiveData<List<Task>> = taskDao.getTasks(false)
    val completedTasks: LiveData<List<Task>> = taskDao.getTasks(true)

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    suspend fun update(task: Task) {
        taskDao.update(task)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }
}