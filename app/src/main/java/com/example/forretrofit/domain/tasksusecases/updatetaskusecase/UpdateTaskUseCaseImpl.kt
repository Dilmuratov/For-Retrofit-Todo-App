package com.example.forretrofit.domain.tasksusecases.updatetaskusecase

import com.example.forretrofit.data.models.createtask.CreateTaskResponseData
import com.example.forretrofit.domain.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class UpdateTaskUseCaseImpl(private val tasksRepository: TasksRepository) : UpdateTaskUseCase {

    override suspend fun execute(token: String, id: Int): Flow<Response<CreateTaskResponseData>> =
        flow {
            tasksRepository.updateTask(token, id).collect {
                emit(it)
            }
        }
}