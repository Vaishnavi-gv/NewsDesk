package com.example.newsdesk.di

import android.content.Context
import com.example.newsdesk.NewsDesk
import com.example.newsdesk.data.web.NewsApi
import com.example.newsdesk.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesApplication(@ApplicationContext context: Context):NewsDesk{
        return context as NewsDesk
    }

    @Singleton
    @Provides
    fun providesContext(@ApplicationContext context: Context):Context{
        return context
    }

    @Singleton
    @Provides
    fun provideRetrofit(client : OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30L,java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(30L,java.util.concurrent.TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }
}