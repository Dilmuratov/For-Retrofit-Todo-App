package com.example.forretrofit.data.models.login

import com.google.gson.annotations.SerializedName

data class LoginResponseData(
    val success: Boolean,
    val code: Int,
    val message: String,
    @SerializedName("payload")
    val loginPayload: LoginPayload
)
