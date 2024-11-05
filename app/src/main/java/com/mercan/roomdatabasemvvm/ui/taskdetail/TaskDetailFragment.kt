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
import com.mercan.roomdatabasemvvm.utils.pickDate
import com.mercan.roomdatabasemvvm.utils.pickTime
import com.mercan.roomdatabasemvvm.viewmodel.TaskViewModel

class TaskDetailFragment : Fragment() {
    private var _binding: FragmentTaskDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TaskViewModel by viewModels()

    private var title = ""
    private var description = ""
    private var isCompleted = false
    private var date = ""
    private var time = ""

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
                date = it.deadLine?.split(" ")?.get(0) ?: ""
                time = it.deadLine?.split(" ")?.get(1) ?: ""

                binding.titleTextInput.setText(title)
                binding.descriptionTextInput.setText(description)
                binding.isCompletedCheckbox.isChecked = isCompleted
                binding.datePickerTextView.text = date
                binding.timePickerTextView.text = time
            }
        }

        setTextChangedListeners()
        setCheckedChangeListeners()
        setButtonOnClicks()
        setDateTimePickerOnClicks()

        return binding.root
    }

    private fun setTextChangedListeners() {
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
    }

    private fun setCheckedChangeListeners() {
        binding.isCompletedCheckbox.setOnCheckedChangeListener { _, isChecked ->
            isCompleted = isChecked
        }
    }

    private fun setButtonOnClicks() {
        binding.updateButton.setOnClickListener {
            val task = viewModel.task.value
            task?.let {
                it.title = title
                it.description = description
                it.isCompleted = isCompleted
                it.deadLine = "$date $time"
                it.completeTime = if (isCompleted) it.completeTime else null
                viewModel.update(it)
            }
            
            findNavController().popBackStack()
        }

        binding.deleteButton.setOnClickListener {
            viewModel.delete(viewModel.task.value!!)
            findNavController().popBackStack()
        }
    }

    private fun setDateTimePickerOnClicks() {
        binding.datePickerCardView.setOnClickListener {
            pickDate(requireActivity()) {
                date = it
                binding.datePickerTextView.text = it
            }
        }

        binding.timePickerCardView.setOnClickListener {
            pickTime(requireActivity()) {
                time = it
                binding.timePickerTextView.text = it
            }
        }
    }
}