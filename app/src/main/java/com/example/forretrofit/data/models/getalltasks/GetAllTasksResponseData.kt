package com.example.forretrofit.data.models.getalltasks

data class GetAllTasksResponseData(
    val success: Boolean,
    val code: Int,
    val message: String,
    val payload: List<Tasks>
)