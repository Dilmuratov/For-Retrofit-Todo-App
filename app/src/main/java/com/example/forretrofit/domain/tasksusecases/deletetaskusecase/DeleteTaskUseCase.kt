package com.example.forretrofit.domain.tasksusecases.deletetaskusecase

import com.example.forretrofit.data.models.createtask.CreateTaskResponseData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface DeleteTaskUseCase {

    suspend fun execute(token: String, id: Int): Flow<Response<CreateTaskResponseData>>

}