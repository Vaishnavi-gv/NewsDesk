package com.example.newsdesk.di

import com.example.newsdesk.data.repository.NewsRepositoryImpl
import com.example.newsdesk.data.web.NewsApi
import com.example.newsdesk.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesNewsRepository(api:NewsApi):NewsRepository{
        return NewsRepositoryImpl(api)
    }
}