package com.enzoftware.composenews.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class News(
    val id: String,
    val image: String,
    val title: String,
    val description: String,
    val url: String,
//    val editor: String,
//    val description: String,
//    val id: String,
//    val image: String,
//    val language: String,
//    val published: String,
//    val title: String,
//    val url: String,
    @Json(name = "category")
    val categories: List<String>,
)