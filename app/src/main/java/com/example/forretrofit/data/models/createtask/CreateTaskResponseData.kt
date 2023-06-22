package com.example.forretrofit.data.models.createtask

data class CreateTaskResponseData(
    val success: Boolean,
    val code: Int,
    val message: String,
    val payload: CreateTaskData
)
