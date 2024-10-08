package com.example.frndassignment.data.models.repository.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class StoreCalendarTaskApiModel(@Json(name = "user_id") val userId: Int,
                                     @Json(name = "task") val taskModel: CalendarTaskApiModel)
