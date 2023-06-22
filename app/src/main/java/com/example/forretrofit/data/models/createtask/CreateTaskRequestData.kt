package com.example.forretrofit.data.models.createtask

import com.google.gson.annotations.SerializedName

data class CreateTaskRequestData(
    @SerializedName("task")
    val taskName: String,
    @SerializedName("category_id")
    val categoryId: Int,
    val description: String? = null
)
