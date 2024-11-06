package com.mercan.roomdatabasemvvm.ui.taskuncompletedlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mercan.roomdatabasemvvm.data.model.Task
import com.mercan.roomdatabasemvvm.databinding.FragmentTaskUncompletedListBinding
import com.mercan.roomdatabasemvvm.ui.tablayout.TabLayoutFragment
import com.mercan.roomdatabasemvvm.ui.tablayout.TabLayoutFragmentDirections
import com.mercan.roomdatabasemvvm.ui.taskuncompletedlist.adapter.TaskUncompletedListAdapter
import com.mercan.roomdatabasemvvm.viewmodel.TaskViewModel

class TaskUncompletedListFragment : Fragment() {
    private var _binding: FragmentTaskUncompletedListBinding? = null
    private val binding get() = _binding!!

    private val taskViewModel: TaskViewModel by viewModels()
    private lateinit var taskUncompletedListAdapter: TaskUncompletedListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskUncompletedListBinding.inflate(inflater, container, false)

        binding.taskUncompletedRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val tabLayoutFragment = parentFragment as? TabLayoutFragment

        taskViewModel.uncompletedTasks.observe(viewLifecycleOwner) { tasks ->
            if (tasks.isNullOrEmpty()) {
                binding.taskUncompletedRecyclerView.visibility = View.GONE
                binding.noTaskTextView.visibility = View.VISIBLE
                return@observe
            } else {
                binding.taskUncompletedRecyclerView.visibility = View.VISIBLE
                binding.noTaskTextView.visibility = View.GONE
            }

            taskUncompletedListAdapter = TaskUncompletedListAdapter(
                tabLayoutFragment?.getFab(),
                requireActivity(),
                tasks,
                ::onCheckClick,
                ::onClick,
            )
            binding.taskUncompletedRecyclerView.adapter = taskUncompletedListAdapter
        }

        return binding.root
    }

    private fun onCheckClick(task: Task) {
        taskViewModel.update(task)
    }

    private fun onClick(id: Int) {
        val dir = TabLayoutFragmentDirections.actionTabLayoutFragmentToTaskDetailFragment()
        dir.id = id
        findNavController().navigate(dir)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}