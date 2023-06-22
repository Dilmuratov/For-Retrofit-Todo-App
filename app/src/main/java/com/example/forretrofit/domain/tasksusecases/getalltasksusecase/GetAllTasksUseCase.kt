package com.example.forretrofit.domain.tasksusecases.getalltasksusecase

import com.example.forretrofit.data.models.getalltasks.GetAllTasksResponseData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface GetAllTasksUseCase {

    suspend fun execute(token: String): Flow<Response<GetAllTasksResponseData>>
}