package com.mercan.roomdatabasemvvm.ui.taskcompletedlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mercan.roomdatabasemvvm.data.model.Task
import com.mercan.roomdatabasemvvm.databinding.FragmentTaskCompletedListBinding
import com.mercan.roomdatabasemvvm.ui.tablayout.TabLayoutFragmentDirections
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
            taskCompletedListAdapter = TaskCompletedListAdapter(tasks, ::onDeleteClick, ::onClick)
            binding.taskCompletedRecyclerView.adapter = taskCompletedListAdapter
        }

        return binding.root
    }

    private fun onDeleteClick(task: Task) {
        taskViewModel.delete(task)
    }

    private fun onClick(id: Int) {
        val dir = TabLayoutFragmentDirections
            .actionTabLayoutFragmentToTaskDetailFragment()
        dir.id = id
        findNavController().navigate(dir)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}