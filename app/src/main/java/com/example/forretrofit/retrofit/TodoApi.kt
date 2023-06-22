package com.example.forretrofit.retrofit

import com.example.forretrofit.data.models.createtask.CreateTaskRequestData
import com.example.forretrofit.data.models.createtask.CreateTaskResponseData
import com.example.forretrofit.data.models.getalltasks.GetAllTasksResponseData
import com.example.forretrofit.data.models.login.LoginRequestData
import com.example.forretrofit.data.models.login.LoginResponseData
import com.example.forretrofit.data.models.login.RegisterRequestData
import retrofit2.Response
import retrofit2.http.*

interface TodoApi {
    @POST("/api/register")
    suspend fun register(@Body body: RegisterRequestData): Response<LoginResponseData>

    @POST("/public/api/login")
    suspend fun login(@Body body: LoginRequestData): Response<LoginResponseData>

    @GET("/api/tasks/")
    suspend fun getAllTasks(@Header("Authorization") token: String): Response<GetAllTasksResponseData>

    @POST("/api/tasks")
    suspend fun createTask(
        @Body body: CreateTaskRequestData,
        @Header("Authorization") token: String
    ): Response<CreateTaskResponseData>

    @DELETE("/api/tasks/{id}")
    suspend fun deleteTask(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<CreateTaskResponseData>

    @PATCH("/api/tasks/{id}")
    suspend fun updateTask(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<CreateTaskResponseData>
}