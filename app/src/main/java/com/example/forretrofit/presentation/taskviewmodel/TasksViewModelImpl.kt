package com.example.forretrofit.presentation.taskviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.forretrofit.data.models.createtask.CreateTaskRequestData
import com.example.forretrofit.data.models.createtask.CreateTaskResponseData
import com.example.forretrofit.data.models.getalltasks.Tasks
import com.example.forretrofit.domain.tasksusecases.createtaskusecase.CreateTaskUseCase
import com.example.forretrofit.domain.tasksusecases.deletetaskusecase.DeleteTaskUseCase
import com.example.forretrofit.domain.tasksusecases.getalltasksusecase.GetAllTasksUseCase
import com.example.forretrofit.domain.tasksusecases.updatetaskusecase.UpdateTaskUseCase
import retrofit2.Response

class TasksViewModelImpl(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : TasksViewModel() {

    private val _getAllTasksLiveData = MutableLiveData<List<Tasks>>()
    override val getAllTasksLiveData: LiveData<List<Tasks>>
        get() = _getAllTasksLiveData

    private val _createTaskLiveData = MutableLiveData<Response<CreateTaskResponseData>>()
    override val createTaskLiveData: LiveData<Response<CreateTaskResponseData>>
        get() = _createTaskLiveData

    override suspend fun getAllTasks(token: String) {
        getAllTasksUseCase.execute(token).collect {
            _getAllTasksLiveData.value = it.body()!!.payload
        }
    }

    override suspend fun createTask(createTaskRequestData: CreateTaskRequestData, token: String) {
        createTaskUseCase.execute(createTaskRequestData, token).collect {
            _createTaskLiveData.value = it
        }
    }

    override suspend fun deleteTask(token: String, id: Int) {
        deleteTaskUseCase.execute(token, id).collect {
            _createTaskLiveData.value = it
        }
    }

    override suspend fun updateTask(token: String, id: Int) {
        updateTaskUseCase.execute(token, id).collect {
            _createTaskLiveData.value = it
        }
    }
}