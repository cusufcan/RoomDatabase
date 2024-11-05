package com.mercan.roomdatabasemvvm.ui.taskcompletedlist.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mercan.roomdatabasemvvm.R
import com.mercan.roomdatabasemvvm.data.model.Task
import com.mercan.roomdatabasemvvm.databinding.TaskCompletedItemBinding

class TaskCompletedItemViewHolder(
    private val binding: TaskCompletedItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        context: Context,
        task: Task,
        onDeleteClicked: (task: Task) -> Unit,
        onClick: (id: Int) -> Unit
    ) {
        binding.titleTextView.text = task.title
        binding.deleteButton.setOnClickListener {
            onDeleteClicked(task)
        }

        task.completeTime?.let {
            val date = it.split(" ")[0]

            binding.completeTimeTextView.visibility = View.VISIBLE
            binding.completeTimeTextView.text = context.getString(R.string.completed_on, date)
        }

        binding.root.setOnClickListener {
            onClick(task.id)
        }
    }
}