package com.mercan.roomdatabasemvvm.ui.taskcompletedlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mercan.roomdatabasemvvm.databinding.FragmentTaskCompletedListBinding

class TaskCompletedListFragment : Fragment() {
    private var _binding: FragmentTaskCompletedListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskCompletedListBinding.inflate(inflater, container, false)

        return binding.root
    }
}