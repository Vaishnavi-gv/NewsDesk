package com.example.newsdesk.domain.repository

import com.example.newsdesk.data.response.NewsResponse
import retrofit2.Response

interface NewsRepository {
    suspend fun getNews(
        language:String,
        text:String?,
        country:String?
    ): Response<NewsResponse>

}