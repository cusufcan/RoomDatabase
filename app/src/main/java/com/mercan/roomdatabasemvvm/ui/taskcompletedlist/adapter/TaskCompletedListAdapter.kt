package com.mercan.roomdatabasemvvm.ui.taskcompletedlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mercan.roomdatabasemvvm.data.model.Task
import com.mercan.roomdatabasemvvm.databinding.TaskCompletedItemBinding

class TaskCompletedListAdapter(
    private val completedTaskList: List<Task>,
    private val onDeleteClicked: (Task) -> Unit
) : RecyclerView.Adapter<TaskCompletedItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskCompletedItemViewHolder {
        val binding = TaskCompletedItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )

        return TaskCompletedItemViewHolder(binding)
    }

    override fun getItemCount() = completedTaskList.size

    override fun onBindViewHolder(holder: TaskCompletedItemViewHolder, position: Int) {
        val task = completedTaskList[position]

        holder.bind(task) {
            onDeleteClicked(it)
        }
    }
}