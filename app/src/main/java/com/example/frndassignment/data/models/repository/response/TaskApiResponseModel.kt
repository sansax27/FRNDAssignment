package com.example.frndassignment.data.models.repository.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TaskApiResponseModel(@Json(name = "title") val title: String,
                                @Json(name = "description") val description: String,)
