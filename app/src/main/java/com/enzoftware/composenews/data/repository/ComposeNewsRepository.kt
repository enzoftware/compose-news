package com.enzoftware.composenews.data.repository


import com.enzoftware.composenews.data.ComposeNewsService
import com.enzoftware.composenews.domain.model.News
import com.enzoftware.composenews.utils.Result
import com.enzoftware.composenews.utils.safeApiCall
import java.io.IOException
import javax.inject.Inject

class ComposeNewsRepository @Inject constructor(private val service: ComposeNewsService) {

    private suspend fun searchNewsByQuery(query: String): Result<List<News>> {
        val result = service.getNewsByKeyword(query)
        return when (result.status) {
            "ok" -> {
                return Result.Success(result.news)
            }
            else -> Result.Error(IOException("Error finding news"))
        }
    }

    private suspend fun retrieveLatestNews(): Result<List<News>> {
        val result = service.getLatestNews()
        return when (result.status) {
            "ok" -> {
                return Result.Success(result.news)
            }
            else -> Result.Error(IOException("Error retrieving news"))
        }
    }

    suspend fun searchNewsByKeywords(query: String) = safeApiCall(
        call = { searchNewsByQuery(query) },
        errorMessage = "Failed to find news with the keywords"
    )

    suspend fun fetchLatestNews() = safeApiCall(
        call = { retrieveLatestNews() },
        errorMessage = "Failed to retrieve latest news"
    )
}


