package com.example.forretrofit.domain.tasksusecases.getalltasksusecase

import com.example.forretrofit.data.models.getalltasks.GetAllTasksResponseData
import com.example.forretrofit.domain.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class GetAllTasksUseCaseImpl(private val tasksRepository: TasksRepository) : GetAllTasksUseCase {

    override suspend fun execute(token: String): Flow<Response<GetAllTasksResponseData>> = flow {
        tasksRepository.getAllTasks(token).collect {
            emit(it)
        }
    }
}