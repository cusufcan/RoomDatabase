package com.mercan.roomdatabasemvvm.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    val title: String,
    val description: String?,
    var isCompleted: Boolean = false,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}