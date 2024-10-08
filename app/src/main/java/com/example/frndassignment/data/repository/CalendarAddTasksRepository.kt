package com.example.frndassignment.data.repository

import com.example.frndassignment.data.ApiService
import com.example.frndassignment.data.NetworkUtils
import com.example.frndassignment.data.models.repository.api.StoreCalendarTaskApiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CalendarAddTasksRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun storeCalendarTask(storeCalendarTasksApiModel: StoreCalendarTaskApiModel) = withContext(Dispatchers.IO)
    {
        NetworkUtils.callApi {
            apiService.storeCalendarTask(storeCalendarTasksApiModel)
        }
    }
}