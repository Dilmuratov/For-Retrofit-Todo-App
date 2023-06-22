package com.example.forretrofit.data.models.getalltasks

import com.google.gson.annotations.SerializedName

data class Category(
    val id: Int,
    @SerializedName("user_id")
    val userId: Int,
    val name: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)
