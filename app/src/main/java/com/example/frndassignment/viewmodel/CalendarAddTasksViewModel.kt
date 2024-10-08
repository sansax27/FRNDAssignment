package com.example.frndassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frndassignment.data.NetworkResult
import com.example.frndassignment.data.UIStatus
import com.example.frndassignment.data.models.repository.api.CalendarTaskApiModel
import com.example.frndassignment.data.models.repository.api.StoreCalendarTaskApiModel
import com.example.frndassignment.data.models.repository.response.TaskApiResponseModel
import com.example.frndassignment.data.models.repository.response.TaskDataApiResponseModel
import com.example.frndassignment.data.repository.CalendarAddTasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random


@HiltViewModel
class CalendarAddTasksViewModel @Inject constructor(private val repository: CalendarAddTasksRepository): ViewModel() {

    private val _storeCalendarTaskStatus: MutableStateFlow<UIStatus<String>> = MutableStateFlow(UIStatus.Empty)
    val storeCalendarTaskStatus: StateFlow<UIStatus<String>>
        get() = _storeCalendarTaskStatus


    fun storeCalendarTask(userId: Int, title: String, description: String) = viewModelScope.launch {
        _storeCalendarTaskStatus.value = UIStatus.Loading
        when(val response = repository.storeCalendarTask(StoreCalendarTaskApiModel(userId, CalendarTaskApiModel(title, description)))) {
            is NetworkResult.Success -> _storeCalendarTaskStatus.value = UIStatus.Success(
                "Success"
            )
            is NetworkResult.Failure -> _storeCalendarTaskStatus.value = UIStatus.Error("Error is ${response.code} ${response.message}")
            is NetworkResult.Exception -> _storeCalendarTaskStatus.value = UIStatus.Error("Error is ${response.e}")
        }
    }

}