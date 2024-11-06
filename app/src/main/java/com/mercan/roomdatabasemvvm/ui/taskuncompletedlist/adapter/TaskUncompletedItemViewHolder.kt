package com.mercan.roomdatabasemvvm.ui.taskuncompletedlist.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mercan.roomdatabasemvvm.R
import com.mercan.roomdatabasemvvm.data.model.Task
import com.mercan.roomdatabasemvvm.databinding.TaskUncompletedItemBinding
import com.mercan.roomdatabasemvvm.utils.showSnackbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TaskUncompletedItemViewHolder(
    private val binding: TaskUncompletedItemBinding,
    private val fabAnchorView: FloatingActionButton?,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        context: Context,
        task: Task,
        onCheck: (task: Task) -> Unit,
        onClick: (id: Int) -> Unit,
    ) {
        binding.titleTextView.text = task.title
        binding.checkbox.isChecked = task.isCompleted
        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            task.isCompleted = isChecked

            if (isChecked) {
                val initialDate = Calendar.getInstance()
                val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val completeDate = formatter.format(initialDate.time)
                task.completeTime = completeDate
            }

            onCheck(task)

            showSnackbar(
                view = binding.root,
                anchorView = fabAnchorView,
                context = context,
                message = context.getString(
                    if (isChecked) R.string.task_completed
                    else R.string.task_uncompleted
                ),
                actionText = context.getString(R.string.undo),
                action = {
                    task.isCompleted = false
                    task.completeTime = ""
                    onCheck(task)
                }
            )
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