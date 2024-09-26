package com.example.newsdesk.data.web

import com.example.newsdesk.data.response.NewsResponse
import com.example.newsdesk.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {

    @GET("search-news")
    suspend fun getNews(
        @Query("country") country:String?,
        @Query("language") language:String,
        @Query("text") text:String?,
        @Query("news-sources") newsSources: String?="https://www.bbc.co.uk",
        @Query("api-key") apikey:String=API_KEY,
    ): Response<NewsResponse>  //that response is retrofit response to tackle error of retrofit if not then NewsResponse showed
}

