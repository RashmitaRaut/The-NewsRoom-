package com.example.thenewsroom.domain.model.repository

import com.example.thenewsroom.domain.model.Article
import com.example.thenewsroom.util.Resource

interface NewsRepository {

    suspend fun getTopHeadlines(
        category: String
    ): Resource<List<Article>>
}