package com.example.newsdesk.presentation.Bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsdesk.data.dao.NewsDao
import com.example.newsdesk.data.database.NewsDatabase
import com.example.newsdesk.data.model.News
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(database: NewsDatabase): ViewModel(){

    private val newsDao =database.newsDao()

    fun getBookmark() = newsDao.getNews()

    fun deleteBookmark(news: News){
        viewModelScope.launch {
            newsDao.deleteNews(news)
        }

    }
}