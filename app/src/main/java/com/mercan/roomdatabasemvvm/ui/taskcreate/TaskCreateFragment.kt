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
import com.mercan.roomdatabasemvvm.utils.getInitialDate
import com.mercan.roomdatabasemvvm.utils.getInitialTime
import com.mercan.roomdatabasemvvm.utils.pickDate
import com.mercan.roomdatabasemvvm.utils.pickTime
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

        setInitialDateTime()

        binding.saveTaskButton.setOnClickListener(::saveTask)

        binding.datePickerCardView.setOnClickListener {
            pickDate(requireActivity()) {
                binding.datePickerTextView.text = it
            }
        }

        binding.timePickerCardView.setOnClickListener {
            pickTime(requireActivity()) {
                binding.timePickerTextView.text = it
            }
        }

        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            binding.dateTimePickerLayout.visibility = if (isChecked) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        return binding.root
    }

    private fun setInitialDateTime() {
        binding.datePickerTextView.text = getInitialDate()
        binding.timePickerTextView.text = getInitialTime()
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

    private fun saveTask(view: View) {
        val title = binding.taskTitleTextInputField.text.toString()
        val description = binding.taskDescriptionTextInputField.text.toString()

        val date = binding.datePickerTextView.text.toString()
        val time = binding.timePickerTextView.text.toString()

        val deadLine = if (binding.checkbox.isChecked) {
            "$date $time"
        } else {
            null
        }

        if (title.trim().isEmpty()) {
            binding.taskTitleTextInputLayout.error = getString(R.string.field_empty_error)
            return
        }

        taskViewModel.insert(Task(title, description, deadLine = deadLine))

        popBack()
    }


    private fun popBack() {
        findNavController().popBackStack()
    }
}