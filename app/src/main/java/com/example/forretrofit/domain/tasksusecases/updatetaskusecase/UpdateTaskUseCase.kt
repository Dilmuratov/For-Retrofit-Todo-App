package com.example.forretrofit.domain.tasksusecases.updatetaskusecase

import com.example.forretrofit.data.models.createtask.CreateTaskResponseData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface UpdateTaskUseCase {

    suspend fun execute(token: String, id: Int): Flow<Response<CreateTaskResponseData>>

}