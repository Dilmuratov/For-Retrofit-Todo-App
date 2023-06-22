package com.example.forretrofit.domain.loginusecases.login

import com.example.forretrofit.data.models.login.LoginRequestData
import com.example.forretrofit.data.models.login.LoginResponseData
import com.example.forretrofit.domain.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class LoginUseCaseImpl(private val loginRepository: LoginRepository) : LoginUseCase {
    override fun execute(loginRequestData: LoginRequestData): Flow<Response<LoginResponseData>> =
        flow {
            loginRepository.login(loginRequestData).collect {
                emit(it)
            }
        }
}