package com.mercan.roomdatabasemvvm.ui.tasklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mercan.roomdatabasemvvm.R
import com.mercan.roomdatabasemvvm.databinding.FragmentTaskListBinding
import com.mercan.roomdatabasemvvm.ui.tasklist.adapter.TaskListAdapter
import com.mercan.roomdatabasemvvm.viewmodel.TaskViewModel

class TaskListFragment : Fragment() {
    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!

    private val taskViewModel: TaskViewModel by viewModels()
    private lateinit var taskListAdapter: TaskListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)

        binding.taskRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        taskViewModel.allTasks.observe(viewLifecycleOwner) { tasks ->
            taskListAdapter = TaskListAdapter(tasks) { task ->
                taskViewModel.update(task)
            }
            binding.taskRecyclerView.adapter = taskListAdapter
        }

        binding.createTaskFloatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_taskListFragment_to_taskCreateFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}