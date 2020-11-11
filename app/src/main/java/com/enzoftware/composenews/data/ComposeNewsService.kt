package com.enzoftware.composenews.data

import com.enzoftware.composenews.data.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ComposeNewsService {

    @GET("latest-news")
    suspend fun getLatestNews(): NewsResponse

    @GET("search?keywords={query}")
    suspend fun getNewsByKeyword(
        @Path("query") query: String,
    ): NewsResponse
}