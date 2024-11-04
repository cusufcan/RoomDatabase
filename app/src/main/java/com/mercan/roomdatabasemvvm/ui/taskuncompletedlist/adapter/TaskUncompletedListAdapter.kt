package com.mercan.roomdatabasemvvm.ui.taskuncompletedlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mercan.roomdatabasemvvm.data.model.Task
import com.mercan.roomdatabasemvvm.databinding.TaskUncompletedItemBinding

class TaskUncompletedListAdapter(
    private val tasks: List<Task>,
    private val onCheck: (task: Task) -> Unit,
) : RecyclerView.Adapter<TaskUncompletedItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskUncompletedItemViewHolder {
        val binding = TaskUncompletedItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )

        return TaskUncompletedItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskUncompletedItemViewHolder, position: Int) {
        holder.bind(tasks[position], onCheck)
    }

    override fun getItemCount() = tasks.size
}