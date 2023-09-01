package com.example.thenewsroom.data.remote

import com.example.thenewsroom.domain.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {


    // https://newsapi.org/v2/top-headlines?country=us&apiKey=75c62a8c721c4bf087a5e71c5202bade
    // Base URL - https://newsapi.org/v2/
    // End point - top-headlines
    // Parameters - country(us), api key

    @GET("top-headlines")
    suspend fun getBreakingNews(
        @Query("category") category: String,
        @Query("country") country: String = "us",
        @Query("apikey") apikey: String = API_KEY,
    ): NewsResponse

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY = "75c62a8c721c4bf087a5e71c5202bade"
    }
}