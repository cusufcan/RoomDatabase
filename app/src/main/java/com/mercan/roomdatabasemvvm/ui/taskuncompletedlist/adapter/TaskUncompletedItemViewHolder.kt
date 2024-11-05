package com.mercan.roomdatabasemvvm.ui.taskuncompletedlist.adapter

import android.view.View
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

        if (task.description.isNullOrEmpty()) {
            binding.descriptionTextView.text = ""
        } else {
            binding.descriptionTextView.text = task.description
            binding.descriptionTextView.visibility = View.VISIBLE
        }

        binding.root.setOnClickListener {
            onClick(task.id)
        }
    }
}