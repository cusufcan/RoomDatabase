package com.mercan.roomdatabasemvvm.ui.taskuncompletedlist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.mercan.roomdatabasemvvm.data.model.Task
import com.mercan.roomdatabasemvvm.databinding.TaskUncompletedItemBinding

class TaskUncompletedItemViewHolder(
    private val binding: TaskUncompletedItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        task: Task,
        onCheck: (task: Task) -> Unit,
        onClick: (id: Int) -> Unit,
    ) {
        binding.titleTextView.text = task.title
        binding.checkbox.isChecked = task.isCompleted
        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            task.isCompleted = isChecked
            onCheck(task)
        }

        binding.root.setOnClickListener {
            onClick(task.id)
        }
    }
}