package com.example.forretrofit.data.models.login

data class LoginPayload(
    val name: String,
    val phone: String,
    val token: String
)