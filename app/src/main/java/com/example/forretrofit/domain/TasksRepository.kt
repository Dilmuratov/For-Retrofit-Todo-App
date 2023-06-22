package com.example.forretrofit.domain

import com.example.forretrofit.data.models.createtask.CreateTaskRequestData
import com.example.forretrofit.data.models.createtask.CreateTaskResponseData
import com.example.forretrofit.data.models.getalltasks.GetAllTasksResponseData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface TasksRepository {

    suspend fun getAllTasks(token: String): Flow<Response<GetAllTasksResponseData>>

    suspend fun createTask(
        createTaskRequestData: CreateTaskRequestData,
        token: String
    ): Flow<Response<CreateTaskResponseData>>

    suspend fun deleteTask(token: String, id: Int): Flow<Response<CreateTaskResponseData>>

    suspend fun updateTask(token: String, id: Int): Flow<Response<CreateTaskResponseData>>
}