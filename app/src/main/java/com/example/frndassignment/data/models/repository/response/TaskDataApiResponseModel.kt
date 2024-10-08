package com.example.frndassignment.data.models.repository.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class TaskDataApiResponseModel(@Json(name = "task_id") val taskId: Int, @Json(name = "task_detail") val taskModel: TaskApiResponseModel)
