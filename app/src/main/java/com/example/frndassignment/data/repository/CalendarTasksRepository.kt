package com.example.frndassignment.data.repository

import com.example.frndassignment.data.ApiService
import com.example.frndassignment.data.NetworkUtils
import com.example.frndassignment.data.models.repository.api.DeleteCalendarTaskApiModel
import com.example.frndassignment.data.models.repository.api.GetCalendarTasksApiModel
import com.example.frndassignment.data.models.repository.api.StoreCalendarTaskApiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CalendarTasksRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getCalendarTasks(userId: Int) = withContext(Dispatchers.IO) {
        NetworkUtils.callApi {
            apiService.getCalendarTasks(GetCalendarTasksApiModel(userId))
        }
    }


    suspend fun deleteCalendarTask(userId: Int, taskId: Int) = withContext(Dispatchers.IO)
    {
        NetworkUtils.callApi {
            apiService.deleteCalendarTask(DeleteCalendarTaskApiModel(userId, taskId))
        }
    }



}