package com.example.forretrofit.data.models.createtask

import com.google.gson.annotations.SerializedName

data class CreateTaskData(
    val id: Int,
    val task: String,
    val description: String,
    @SerializedName("is_done")
    val isDone: Boolean,
)
