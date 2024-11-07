package com.mercan.roomdatabasemvvm.ui.taskdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mercan.roomdatabasemvvm.R
import com.mercan.roomdatabasemvvm.databinding.FragmentTaskDetailBinding
import com.mercan.roomdatabasemvvm.utils.pickDate
import com.mercan.roomdatabasemvvm.utils.pickTime
import com.mercan.roomdatabasemvvm.utils.showDialog
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

        setObservers()
        setTextChangedListeners()
        setCheckedChangeListeners()
        setButtonOnClicks()
        setDateTimePickerOnClicks()

        return binding.root
    }

    private fun setObservers() {
        val args = TaskDetailFragmentArgs.fromBundle(requireArguments())
        viewModel.find(args.id)
        viewModel.task.observe(viewLifecycleOwner) { task ->
            task?.let {
                title = it.title
                description = it.description ?: ""
                isCompleted = it.isCompleted

                it.deadline?.let { deadLine ->
                    binding.taskDeadLineLayout.visibility = View.VISIBLE

                    date = deadLine.split(" ")[0]
                    time = deadLine.split(" ")[1]
                }

                binding.titleTextInput.setText(title)
                binding.descriptionTextInput.setText(description)
                binding.isCompletedCheckbox.isChecked = isCompleted
                binding.datePickerTextView.text = date
                binding.timePickerTextView.text = time
            }
        }
    }

    private fun setTextChangedListeners() {
        binding.titleTextInput.addTextChangedListener {
            if (it.toString().trim().isEmpty()) {
                binding.titleTextInput.error = getString(R.string.field_empty_error)
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
            showDialog(
                context = requireActivity(),
                message = getString(R.string.update_warning),
                positiveButtonAction = {
                    val task = viewModel.task.value
                    task?.let {
                        it.title = title
                        it.description = description
                        it.isCompleted = isCompleted
                        it.deadline = if (it.deadline.isNullOrEmpty()) null else "$date $time"
                        it.completeTime = if (isCompleted) it.completeTime else null
                        viewModel.update(it)
                    }
                    findNavController().popBackStack()
                }
            )
        }

        binding.deleteButton.setOnClickListener {
            showDialog(
                context = requireActivity(),
                message = getString(R.string.delete_warning),
                positiveButtonAction = {
                    viewModel.delete(viewModel.task.value!!)
                    findNavController().popBackStack()
                }
            )
        }
    }

    private fun setDateTimePickerOnClicks() {
        binding.datePickerCardView.setOnClickListener {
            pickDate(requireActivity()) {
                date = it
                binding.datePickerTextView.text = it
                pickTimeManuel()
            }
        }

        binding.timePickerCardView.setOnClickListener {
            pickTimeManuel()
        }
    }

    private fun pickTimeManuel() {
        pickTime(requireActivity()) {
            time = it
            binding.timePickerTextView.text = it
        }
    }
}