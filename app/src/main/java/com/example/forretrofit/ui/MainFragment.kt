package com.example.forretrofit.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.forretrofit.R
import com.example.forretrofit.databinding.FragmentMainBinding
import com.example.forretrofit.presentation.taskviewmodel.TasksViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private lateinit var pref: SharedPreferences
    private val tasksAdapter = TasksAdapter()
    private val tasksViewModel: TasksViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        initObservers()

        swipeRefreshLayout()

        initListeners()
    }

    private fun initObservers() {
        pref = requireContext().getSharedPreferences("myPref", Context.MODE_PRIVATE)

        binding.rvTasks.adapter = tasksAdapter

        lifecycleScope.launch {
            tasksViewModel.getAllTasksLiveData.observe(requireActivity()) {
                tasksAdapter.submitList(it)
            }
        }

        lifecycleScope.launch {
            tasksViewModel.getAllTasks("Bearer ${pref.getString("token", "")}").toString()
        }

    }

    private fun swipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = true

            lifecycleScope.launch {
                tasksViewModel.getAllTasks("Bearer ${pref.getString("token", "")}").toString()
            }

            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun initListeners() {
        binding.btnCreateTask.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("isCreate", true)
            findNavController().navigate(R.id.action_mainFragment_to_taskFragment, bundle)
        }

        tasksAdapter.setOnItemClickListener {
            val bundle = Bundle()
            bundle.putInt("taskId", it.id)
            bundle.putString("taskName", it.task)
            bundle.putInt("categoryId", it.categoryId)
            bundle.putString("description", it.description)
            findNavController().navigate(R.id.action_mainFragment_to_taskFragment, bundle)
        }
    }
}