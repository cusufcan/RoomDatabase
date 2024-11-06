package com.mercan.roomdatabasemvvm.ui.taskuncompletedlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mercan.roomdatabasemvvm.data.model.Task
import com.mercan.roomdatabasemvvm.databinding.TaskUncompletedItemBinding

class TaskUncompletedListAdapter(
    private val fabAnchorView: FloatingActionButton?,
    private val context: Context,
    private val tasks: List<Task>,
    private val onCheck: (task: Task) -> Unit,
    private val onClick: (id: Int) -> Unit,
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

        return TaskUncompletedItemViewHolder(binding, fabAnchorView)
    }

    override fun onBindViewHolder(holder: TaskUncompletedItemViewHolder, position: Int) {
        holder.bind(context, tasks[position], onCheck, onClick)
    }

    override fun getItemCount() = tasks.size
}