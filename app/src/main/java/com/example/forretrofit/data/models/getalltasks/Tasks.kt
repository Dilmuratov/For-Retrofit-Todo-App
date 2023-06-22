package com.example.forretrofit.data.models.getalltasks

import com.google.gson.annotations.SerializedName

data class Tasks(
    val id: Int,
    val task: String,
    val description: String,
    @SerializedName("is_done")
    val isDone: Boolean,
    @SerializedName("category_id")
    val categoryId: Int,
    val category: Category
)
