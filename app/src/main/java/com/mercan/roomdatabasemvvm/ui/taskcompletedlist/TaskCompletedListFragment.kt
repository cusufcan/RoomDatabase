package com.mercan.roomdatabasemvvm.ui.taskcompletedlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mercan.roomdatabasemvvm.databinding.FragmentTaskCompletedListBinding
import com.mercan.roomdatabasemvvm.ui.taskcompletedlist.adapter.TaskCompletedListAdapter
import com.mercan.roomdatabasemvvm.viewmodel.TaskViewModel

class TaskCompletedListFragment : Fragment() {
    private var _binding: FragmentTaskCompletedListBinding? = null
    private val binding get() = _binding!!

    private val taskViewModel: TaskViewModel by viewModels()
    private lateinit var taskCompletedListAdapter: TaskCompletedListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskCompletedListBinding.inflate(inflater, container, false)

        binding.taskCompletedRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        taskViewModel.completedTasks.observe(viewLifecycleOwner) { tasks ->
            taskCompletedListAdapter = TaskCompletedListAdapter(tasks) { task ->
                taskViewModel.delete(task)
            }
            binding.taskCompletedRecyclerView.adapter = taskCompletedListAdapter
        }

        return binding.root
    }
}