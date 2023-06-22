package com.example.forretrofit.domain.tasksusecases.createtaskusecase

import com.example.forretrofit.data.models.createtask.CreateTaskRequestData
import com.example.forretrofit.data.models.createtask.CreateTaskResponseData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CreateTaskUseCase {

    suspend fun execute(createTaskRequestData: CreateTaskRequestData, token: String): Flow<Response<CreateTaskResponseData>>

}