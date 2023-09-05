package com.example.thenewsroom.ui.theme.news_screen

import com.example.thenewsroom.domain.model.Article
import retrofit2.http.Query

data class NewsScreenState(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val error: String? = null,
    val isSearchBarVisible: Boolean = false,
    val selectedArticle: Article? = null,
    val category: String = "General",
    val searchQuery: String = " "
)
