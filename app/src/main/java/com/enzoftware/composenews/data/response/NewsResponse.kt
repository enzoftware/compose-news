package com.enzoftware.composenews.data.response

import com.enzoftware.composenews.domain.model.News
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsResponse(
    @Json(name = "news")
    val news: List<News>,
    val page: Int,
    val status: String
)