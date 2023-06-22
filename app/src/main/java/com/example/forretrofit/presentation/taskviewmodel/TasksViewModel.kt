package com.example.forretrofit.presentation.taskviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.forretrofit.data.models.createtask.CreateTaskRequestData
import com.example.forretrofit.data.models.createtask.CreateTaskResponseData
import com.example.forretrofit.data.models.getalltasks.Tasks
import retrofit2.Response

abstract class TasksViewModel : ViewModel() {

    abstract val getAllTasksLiveData: LiveData<List<Tasks>>
    abstract val createTaskLiveData: LiveData<Response<CreateTaskResponseData>>

    abstract suspend fun getAllTasks(token: String)

    abstract suspend fun createTask(createTaskRequestData: CreateTaskRequestData, token: String)

    abstract suspend fun deleteTask(token: String, id: Int)

    abstract suspend fun updateTask(token: String, id: Int)
}