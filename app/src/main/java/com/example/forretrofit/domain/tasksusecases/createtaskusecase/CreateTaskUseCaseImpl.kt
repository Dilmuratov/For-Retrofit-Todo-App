package com.example.forretrofit.domain.tasksusecases.createtaskusecase

import com.example.forretrofit.data.models.createtask.CreateTaskRequestData
import com.example.forretrofit.data.models.createtask.CreateTaskResponseData
import com.example.forretrofit.domain.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class CreateTaskUseCaseImpl(private val tasksRepository: TasksRepository) : CreateTaskUseCase {

    override suspend fun execute(createTaskRequestData: CreateTaskRequestData, token: String): Flow<Response<CreateTaskResponseData>> =
        flow {
            tasksRepository.createTask(createTaskRequestData, token).collect {
                emit(it)
            }
        }
}