package com.example.forretrofit.domain.loginusecases.register

import com.example.forretrofit.data.models.login.LoginResponseData
import com.example.forretrofit.data.models.login.RegisterRequestData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RegisterUseCase {
    suspend fun execute(registerRequestData: RegisterRequestData): Flow<Response<LoginResponseData>>
}