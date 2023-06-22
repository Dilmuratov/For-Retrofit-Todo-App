package com.example.forretrofit.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.forretrofit.R
import com.example.forretrofit.data.models.createtask.CreateTaskRequestData
import com.example.forretrofit.databinding.FragmentTaskBinding
import com.example.forretrofit.presentation.taskviewmodel.TasksViewModel
import com.example.forretrofit.utils.log
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class TaskFragment : Fragment(R.layout.fragment_task) {
    private lateinit var binding: FragmentTaskBinding
    private val tasksViewModel: TasksViewModel by viewModel()
    private var isCreate: Boolean = false
    private var taskId: Int = 0
    private var taskName: String = ""
    private var categoryId: Int = 0
    private var description: String = ""
    private lateinit var pref: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTaskBinding.bind(view)
        pref = requireContext().getSharedPreferences("myPref", Context.MODE_PRIVATE)

        initObservers()

        initVariables()

        initListeners()

        fillData()
    }

    private fun initObservers() {
        tasksViewModel.createTaskLiveData.observe(requireActivity()) {
            if (it.isSuccessful) {
                Log.d(log, it.body().toString())
                findNavController().navigate(R.id.action_taskFragment_to_mainFragment)
            } else {
                Log.d(log, it.errorBody().toString())
            }
            Toast.makeText(requireContext(), it.body()!!.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun createTask() {
        val taskName = binding.etTitle.text.toString()
        val description = binding.etDescription.text.toString()
        if (taskName.isNotEmpty()) {
            lifecycleScope.launch {
                val createTaskRequestData = CreateTaskRequestData(
                    taskName = taskName,
                    categoryId = Random.nextInt(1, 10),
                    description = description
                )
                Log.d(
                    log,
                    "1234" + createTaskRequestData.taskName + " " + createTaskRequestData.description + " " + createTaskRequestData.categoryId
                )
                tasksViewModel.createTask(
                    createTaskRequestData,
                    "Bearer ${pref.getString("token", "")}"
                )
            }
        } else {
            binding.tvTasksName.error = "Please enter a task name!"
        }
    }

    private fun initListeners() {
        binding.ivSave.setOnClickListener {
            if (isCreate) {
                createTask()
            }
        }

        binding.ivDelete.setOnClickListener {
            if (isCreate.not()) {
                deleteTask()
            }
        }
    }

    private fun deleteTask() {
        lifecycleScope.launch {
            tasksViewModel.deleteTask(
                "Bearer ${pref.getString("token", "")}",
                arguments!!.getInt("taskId", 0)
            )
            Log.d(log, "taskId: $taskId")
        }
    }

    private fun updateTask() {

    }

    private fun initVariables() {
        val b = arguments!!
        isCreate = b.getBoolean("isCreate")
        taskId = b.getInt("taskId", 0)
        taskName = b.getString("taskName", "")
        categoryId = b.getInt("categoryId", 0)
        description = b.getString("description", "")
    }

    private fun fillData() {
        if (isCreate) {
            binding.tvTitle.text = "Create Task"
            binding.ivDelete.visibility = View.GONE
        } else {
            binding.tvTitle.text = "Edit Task"
            binding.ivDelete.visibility = View.VISIBLE
            binding.etTitle.setText(taskName)
            binding.etDescription.setText(description)
        }
    }
}