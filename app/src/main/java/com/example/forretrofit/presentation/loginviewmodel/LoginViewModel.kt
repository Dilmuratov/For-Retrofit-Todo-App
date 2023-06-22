package com.example.forretrofit.presentation.loginviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.forretrofit.data.models.login.LoginRequestData
import com.example.forretrofit.data.models.login.LoginResponseData
import com.example.forretrofit.data.models.login.RegisterRequestData
import retrofit2.Response

abstract class LoginViewModel : ViewModel() {

    abstract val loginResponseLiveData: LiveData<Response<LoginResponseData>>

    abstract suspend fun login(loginRequestData: LoginRequestData)

    abstract suspend fun register(registerRequestData: RegisterRequestData)
}