package com.example.forretrofit.domain.loginusecases.login

import com.example.forretrofit.data.models.login.LoginRequestData
import com.example.forretrofit.data.models.login.LoginResponseData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface LoginUseCase {
    fun execute(loginRequestData: LoginRequestData): Flow<Response<LoginResponseData>>
}