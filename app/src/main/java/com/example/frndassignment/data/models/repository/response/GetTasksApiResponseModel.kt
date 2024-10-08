package com.example.frndassignment.data.models.repository.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass




@JsonClass(generateAdapter = true)
data class GetTasksApiResponseModel(@Json(name = "tasks") val tasks: List<TaskDataApiResponseModel>)