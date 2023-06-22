package com.example.forretrofit.domain.loginusecases.register

import com.example.forretrofit.data.models.login.LoginResponseData
import com.example.forretrofit.data.models.login.RegisterRequestData
import com.example.forretrofit.domain.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class RegisterUseCaseImpl(private val loginRepository: LoginRepository) : RegisterUseCase {
    override suspend fun execute(registerRequestData: RegisterRequestData): Flow<Response<LoginResponseData>> =
        flow {
            loginRepository.register(registerRequestData).collect {
                emit(it)
            }
        }
}