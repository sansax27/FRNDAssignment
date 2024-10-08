package com.example.frndassignment.data.models.repository.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetCalendarTasksApiModel(@Json(name = "user_id") val userId: Int)
