package com.mercan.roomdatabasemvvm.ui.tasklist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.mercan.roomdatabasemvvm.data.model.Task
import com.mercan.roomdatabasemvvm.databinding.TaskItemBinding

class TaskItemViewHolder(
    private val binding: TaskItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        task: Task,
        onCheck: (task: Task) -> Unit,
    ) {
        binding.title.text = task.title
        binding.checkbox.isChecked = task.isCompleted
        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            task.isCompleted = isChecked
            onCheck(task)
        }
    }
}