package com.mercan.roomdatabasemvvm.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    var title: String,
    var description: String?,
    var deadLine: String?,
    var isCompleted: Boolean = false,
    var completeTime: String? = null,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}