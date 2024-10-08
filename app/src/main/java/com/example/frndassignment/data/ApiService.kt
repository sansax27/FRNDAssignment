package com.example.frndassignment.data

import com.example.frndassignment.data.models.repository.api.DeleteCalendarTaskApiModel
import com.example.frndassignment.data.models.repository.api.GetCalendarTasksApiModel
import com.example.frndassignment.data.models.repository.api.StoreCalendarTaskApiModel
import com.example.frndassignment.data.models.repository.response.GetTasksApiResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {


    @POST("api/getCalendarTaskList")
    suspend fun getCalendarTasks(@Body getTasksBody: GetCalendarTasksApiModel): Response<GetTasksApiResponseModel>

    @POST("api/deleteCalendarTask")
    suspend fun deleteCalendarTask(@Body deleteTasksBody: DeleteCalendarTaskApiModel): Response<Any>


    @POST("api/storeCalendarTask")
    suspend fun storeCalendarTask(@Body storeTaskBody: StoreCalendarTaskApiModel): Response<Any>

}