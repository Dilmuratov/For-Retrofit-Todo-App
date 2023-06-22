package com.example.forretrofit.di

import com.example.forretrofit.presentation.loginviewmodel.LoginViewModel
import com.example.forretrofit.presentation.loginviewmodel.LoginViewModelImpl
import com.example.forretrofit.presentation.taskviewmodel.TasksViewModel
import com.example.forretrofit.presentation.taskviewmodel.TasksViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<LoginViewModel> {
        LoginViewModelImpl(
            loginUseCase = get(),
            registerUseCase = get()
        )
    }

    viewModel<TasksViewModel> {
        TasksViewModelImpl(
            getAllTasksUseCase = get(),
            createTaskUseCase = get(),
            deleteTaskUseCase = get(),
            updateTaskUseCase = get()
        )
    }
}