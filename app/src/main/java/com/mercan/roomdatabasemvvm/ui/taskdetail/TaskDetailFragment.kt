package com.mercan.roomdatabasemvvm.ui.taskdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mercan.roomdatabasemvvm.databinding.FragmentTaskDetailBinding
import com.mercan.roomdatabasemvvm.viewmodel.TaskViewModel

class TaskDetailFragment : Fragment() {
    private var _binding: FragmentTaskDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TaskViewModel by viewModels()

    private var title = ""
    private var description = ""
    private var isCompleted = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskDetailBinding.inflate(inflater, container, false)

        val args = TaskDetailFragmentArgs.fromBundle(requireArguments())

        viewModel.find(args.id)
        viewModel.task.observe(viewLifecycleOwner) { task ->
            task?.let {
                title = it.title
                description = it.description ?: ""
                isCompleted = it.isCompleted

                binding.titleTextInput.setText(title)
                binding.descriptionTextInput.setText(description)
                binding.isCompletedCheckbox.isChecked = isCompleted
            }
        }

        binding.titleTextInput.addTextChangedListener {
            if (it.toString().trim().isEmpty()) {
                binding.titleTextInput.error = "Please enter a title"
            } else {
                title = it.toString().trim()
                binding.titleTextInput.error = null
            }
        }

        binding.descriptionTextInput.addTextChangedListener {
            description = it.toString().trim()
        }

        binding.isCompletedCheckbox.setOnCheckedChangeListener { _, isChecked ->
            isCompleted = isChecked
        }

        binding.updateButton.setOnClickListener {
            val task = viewModel.task.value
            task?.let {
                it.title = title
                it.description = description
                it.isCompleted = isCompleted
                viewModel.update(it)
            }

            findNavController().popBackStack()
        }

        binding.deleteButton.setOnClickListener {
            viewModel.delete(viewModel.task.value!!)
            findNavController().popBackStack()
        }

        return binding.root
    }
}