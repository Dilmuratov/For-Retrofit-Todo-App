package com.example.forretrofit.domain.tasksusecases.deletetaskusecase

import com.example.forretrofit.data.models.createtask.CreateTaskResponseData
import com.example.forretrofit.domain.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class DeleteTaskUseCaseImpl(private val tasksRepository: TasksRepository) : DeleteTaskUseCase {

    override suspend fun execute(token: String, id: Int): Flow<Response<CreateTaskResponseData>> =
        flow {
            tasksRepository.deleteTask(token, id).collect {
                emit(it)
            }
        }
}