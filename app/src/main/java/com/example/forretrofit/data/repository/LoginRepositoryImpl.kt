package com.example.forretrofit.data.repository

import android.util.Log
import com.example.forretrofit.data.models.login.LoginRequestData
import com.example.forretrofit.data.models.login.LoginResponseData
import com.example.forretrofit.data.models.login.RegisterRequestData
import com.example.forretrofit.domain.LoginRepository
import com.example.forretrofit.retrofit.TodoApi
import com.example.forretrofit.utils.baseUrl
import com.example.forretrofit.utils.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginRepositoryImpl : LoginRepository {

    private var todoApi: TodoApi

    override suspend fun login(loginRequestData: LoginRequestData): Flow<Response<LoginResponseData>> =
        flow {
            val response = todoApi.login(loginRequestData)
            if (response.isSuccessful) {
                emit(response)
            } else {
                Log.d(log, "Login: Error")
            }
        }.catch { it.printStackTrace() }

    override suspend fun register(registerRequestData: RegisterRequestData): Flow<Response<LoginResponseData>> =
        flow {
            val response = todoApi.register(registerRequestData)
            if (response.isSuccessful) {
                emit(response)
            } else {
                Log.d(log, "Register: Error")
            }
        }.catch { it.printStackTrace() }



    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

        todoApi = retrofit.create(TodoApi::class.java)
    }
}