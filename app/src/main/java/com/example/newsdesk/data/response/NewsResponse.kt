package com.example.newsdesk.data.response

import com.example.newsdesk.data.model.News

data class NewsResponse(
    val available: Int,
    val news: List<News>,
    val number: Int,
    val offset: Int
)