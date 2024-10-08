package com.example.frndassignment.data.models.repository.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CalendarTaskApiModel(@Json(name = "title") val title: String, @Json(name = "description") val description: String)
