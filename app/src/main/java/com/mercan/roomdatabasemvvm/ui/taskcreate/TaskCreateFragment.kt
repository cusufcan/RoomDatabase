package com.mercan.roomdatabasemvvm.ui.taskcreate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mercan.roomdatabasemvvm.R
import com.mercan.roomdatabasemvvm.data.model.Task
import com.mercan.roomdatabasemvvm.databinding.FragmentTaskCreateBinding
import com.mercan.roomdatabasemvvm.viewmodel.TaskViewModel

class TaskCreateFragment : Fragment() {
    private var _binding: FragmentTaskCreateBinding? = null
    private val binding get() = _binding!!

    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskCreateBinding.inflate(inflater, container, false)

        checkFields()

        binding.saveTaskButton.setOnClickListener {
            val title = binding.taskTitleTextInputField.text.toString()
            val description = binding.taskDescriptionTextInputField.text.toString()

            if (title.isEmpty()) {
                binding.taskTitleTextInputLayout.error = getString(R.string.field_empty_error)
                return@setOnClickListener
            }

            taskViewModel.insert(Task(title, description))

            popBack()
        }

        return binding.root
    }

    private fun checkFields() {
        binding.taskTitleTextInputField.addTextChangedListener {
            if (it.toString().trim().isEmpty()) {
                binding.taskTitleTextInputLayout.error = getString(R.string.field_empty_error)
            } else {
                binding.taskTitleTextInputLayout.error = null
            }
        }
    }

    private fun popBack() {
        findNavController().popBackStack()
    }
}