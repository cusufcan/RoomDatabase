package com.mercan.roomdatabasemvvm.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String?,
    val isCompleted: Boolean = false,
    val dueDate: Long?,
    val category: String?,
)