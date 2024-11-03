package com.mercan.roomdatabasemvvm.ui.taskcreate

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
        setAutoFocus()

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

    private fun setAutoFocus() {
        binding.taskTitleTextInputField.requestFocus()
        binding.taskTitleTextInputField.postDelayed({
            showKeyboard()
        }, 100)
    }

    private fun showKeyboard() {
        val imm = requireContext()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.taskTitleTextInputField, InputMethodManager.SHOW_IMPLICIT)
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