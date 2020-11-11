package com.enzoftware.composenews.domain.usecase

import com.enzoftware.composenews.data.repository.ComposeNewsRepository
import com.enzoftware.composenews.domain.model.News
import com.enzoftware.composenews.utils.Result
import javax.inject.Inject

class NewsUseCase @Inject constructor(private val repository: ComposeNewsRepository) {
    suspend fun fetchLatestNews(): Result <List<News>> = repository.fetchLatestNews()
    suspend fun searchNewsByQuery(query: String): Result<List<News>> =
        repository.searchNewsByKeywords(query)

}