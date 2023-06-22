package com.example.forretrofit.data.repository

import android.util.Log
import com.example.forretrofit.data.models.createtask.CreateTaskRequestData
import com.example.forretrofit.data.models.createtask.CreateTaskResponseData
import com.example.forretrofit.data.models.getalltasks.GetAllTasksResponseData
import com.example.forretrofit.domain.TasksRepository
import com.example.forretrofit.retrofit.TodoApi
import com.example.forretrofit.utils.baseUrl
import com.example.forretrofit.utils.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TasksRepositoryImpl : TasksRepository {

    private val todoApi: TodoApi

    override suspend fun getAllTasks(token: String): Flow<Response<GetAllTasksResponseData>> =
        flow {
            val response = todoApi.getAllTasks(token = token)
            if (response.isSuccessful) {
                emit(response)
            } else Log.d(log, "Get all tasks: Error")
        }.catch { it.printStackTrace() }

    override suspend fun createTask(
        createTaskRequestData: CreateTaskRequestData,
        token: String
    ): Flow<Response<CreateTaskResponseData>> = flow {
        val response = todoApi.createTask(createTaskRequestData, token)
        if (response.isSuccessful) {
            emit(response)
        } else Log.d(log, "Create task: Error")
    }.catch { it.printStackTrace() }.flowOn(Dispatchers.IO)

    override suspend fun deleteTask(
        token: String,
        id: Int
    ): Flow<Response<CreateTaskResponseData>> = flow {
        val response = todoApi.deleteTask(token, id)
        if (response.isSuccessful) {
            emit(response)
        } else Log.d(log, "Delete task: Error")
    }.catch { it.printStackTrace() }

    override suspend fun updateTask(
        token: String,
        id: Int
    ): Flow<Response<CreateTaskResponseData>> = flow {
        val response = todoApi.updateTask(token, id)
        if (response.isSuccessful) {
            emit(response)
        } else Log.d(log, "Update task: Error")
    }.catch { it.printStackTrace() }

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

        todoApi = retrofit.create(TodoApi::class.java)
    }
}