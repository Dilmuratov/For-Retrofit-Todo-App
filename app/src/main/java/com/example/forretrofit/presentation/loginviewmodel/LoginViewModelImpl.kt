package com.example.forretrofit.presentation.loginviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.forretrofit.data.models.login.LoginRequestData
import com.example.forretrofit.data.models.login.LoginResponseData
import com.example.forretrofit.data.models.login.RegisterRequestData
import com.example.forretrofit.domain.loginusecases.login.LoginUseCase
import com.example.forretrofit.domain.loginusecases.register.RegisterUseCase
import retrofit2.Response

class LoginViewModelImpl(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : LoginViewModel() {

    private val _loginResponseLiveData = MutableLiveData<Response<LoginResponseData>>()
    override val loginResponseLiveData: LiveData<Response<LoginResponseData>>
        get() = _loginResponseLiveData

    override suspend fun login(loginRequestData: LoginRequestData) {
        loginUseCase.execute(loginRequestData).collect {
            _loginResponseLiveData.value = it
        }
    }

    override suspend fun register(registerRequestData: RegisterRequestData) {
        registerUseCase.execute(registerRequestData).collect {
            _loginResponseLiveData.value = it
        }
    }
}