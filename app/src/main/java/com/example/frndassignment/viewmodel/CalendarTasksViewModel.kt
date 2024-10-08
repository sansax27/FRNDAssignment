package com.example.frndassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frndassignment.data.NetworkResult
import com.example.frndassignment.data.UIStatus
import com.example.frndassignment.data.models.repository.response.GetTasksApiResponseModel
import com.example.frndassignment.data.repository.CalendarTasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CalendarTasksViewModel @Inject constructor(private val repository: CalendarTasksRepository): ViewModel() {


    private val _getCalendarTasksStatus = MutableStateFlow<UIStatus<GetTasksApiResponseModel>>(UIStatus.Empty)
    val getCalendarTasksStatus:StateFlow<UIStatus<GetTasksApiResponseModel>>
        get() = _getCalendarTasksStatus

    private val _deleteCalendarTaskStatus = MutableStateFlow<UIStatus<String>>(UIStatus.Empty)
    val deleteCalendarTaskStatus: StateFlow<UIStatus<String>>
        get() = _deleteCalendarTaskStatus


    fun getCalendarTasks(userId: Int) = viewModelScope.launch {
        _getCalendarTasksStatus.value = UIStatus.Loading
        when(val response = repository.getCalendarTasks(userId)) {
            is NetworkResult.Success -> _getCalendarTasksStatus.value = UIStatus.Success(response.data)
            is NetworkResult.Failure -> _getCalendarTasksStatus.value = UIStatus.Error("Error is ${response.code} ${response.message}")
            is NetworkResult.Exception -> _getCalendarTasksStatus.value = UIStatus.Error("Error is ${response.e}")
        }
    }

    fun deleteCalendarTask(userId: Int, taskId: Int) = viewModelScope.launch {
        _deleteCalendarTaskStatus.value = UIStatus.Loading
        when(val response = repository.deleteCalendarTask(userId, taskId)) {
            is NetworkResult.Success -> _deleteCalendarTaskStatus.value = UIStatus.Success("Success")
            is NetworkResult.Failure -> _deleteCalendarTaskStatus.value = UIStatus.Error("Error is ${response.code} ${response.message}")
            is NetworkResult.Exception -> _deleteCalendarTaskStatus.value = UIStatus.Error("Error is ${response.e}")
        }
    }


}