package com.mercan.roomdatabasemvvm.ui.taskcompletedlist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.mercan.roomdatabasemvvm.data.model.Task
import com.mercan.roomdatabasemvvm.databinding.TaskCompletedItemBinding

class TaskCompletedItemViewHolder(
    private val binding: TaskCompletedItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(task: Task, onDeleteClicked: (task: Task) -> Unit) {
        binding.titleTextView.text = task.title
        binding.deleteButton.setOnClickListener {
            onDeleteClicked(task)
        }
    }
}