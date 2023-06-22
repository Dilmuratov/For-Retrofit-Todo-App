package com.example.forretrofit.domain

import com.example.forretrofit.data.models.login.LoginRequestData
import com.example.forretrofit.data.models.login.LoginResponseData
import com.example.forretrofit.data.models.login.RegisterRequestData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface LoginRepository {

    suspend fun login(loginRequestData: LoginRequestData): Flow<Response<LoginResponseData>>

    suspend fun register(registerRequestData: RegisterRequestData): Flow<Response<LoginResponseData>>
}