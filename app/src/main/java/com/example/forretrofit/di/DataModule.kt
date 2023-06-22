package com.example.forretrofit.di

import com.example.forretrofit.data.repository.LoginRepositoryImpl
import com.example.forretrofit.data.repository.TasksRepositoryImpl
import com.example.forretrofit.domain.LoginRepository
import com.example.forretrofit.domain.TasksRepository
import com.example.forretrofit.domain.loginusecases.login.LoginUseCase
import com.example.forretrofit.domain.loginusecases.login.LoginUseCaseImpl
import com.example.forretrofit.domain.loginusecases.register.RegisterUseCase
import com.example.forretrofit.domain.loginusecases.register.RegisterUseCaseImpl
import com.example.forretrofit.domain.tasksusecases.createtaskusecase.CreateTaskUseCase
import com.example.forretrofit.domain.tasksusecases.createtaskusecase.CreateTaskUseCaseImpl
import com.example.forretrofit.domain.tasksusecases.deletetaskusecase.DeleteTaskUseCase
import com.example.forretrofit.domain.tasksusecases.deletetaskusecase.DeleteTaskUseCaseImpl
import com.example.forretrofit.domain.tasksusecases.getalltasksusecase.GetAllTasksUseCase
import com.example.forretrofit.domain.tasksusecases.getalltasksusecase.GetAllTasksUseCaseImpl
import com.example.forretrofit.domain.tasksusecases.updatetaskusecase.UpdateTaskUseCase
import com.example.forretrofit.domain.tasksusecases.updatetaskusecase.UpdateTaskUseCaseImpl
import org.koin.dsl.module

val dataModule = module {
    single<LoginRepository> {
        LoginRepositoryImpl()
    }

    factory<LoginUseCase> {
        LoginUseCaseImpl(get())
    }

    factory<RegisterUseCase> {
        RegisterUseCaseImpl(get())
    }

    single<TasksRepository> {
        TasksRepositoryImpl()
    }

    factory<GetAllTasksUseCase> {
        GetAllTasksUseCaseImpl(get())
    }

    factory<CreateTaskUseCase> {
        CreateTaskUseCaseImpl(get())
    }

    factory<DeleteTaskUseCase> {
        DeleteTaskUseCaseImpl(get())
    }

    factory<UpdateTaskUseCase> {
        UpdateTaskUseCaseImpl(get())
    }
}